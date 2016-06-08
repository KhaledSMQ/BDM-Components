require "logstash/inputs/eventlog"

class LogStash::Inputs::OpenbusEventlog < LogStash::Inputs::EventLog

  config_name "openbus_eventlog"
  milestone 1

  public
  def register
    super
    @user_map = {}
  end

  protected
  def decorate event
    super
    enrich event
  end

  private
  def enrich event
    event_user = event["User"]

    unless event_user.nil? || event_user.empty?

      if @user_map[event_user].nil?
        splitted = event_user.split("\\")
        wmi_user_query = "Select * from Win32_UserAccount Where Domain = '#{splitted[0]}' and Name = '#{splitted[1]}'"
        result = @wmi.ExecQuery(wmi_user_query)

        result.each do |found|
          user_data = {}
          user_data[:SID] = found[:SID]
          user_data[:SIDType] = found[:SIDTYPE]

          @user_map[event_user] = user_data
        end
      end

      event["sid"] = @user_map[event_user][:SID]
      event["sidType"] = @user_map[event_user][:SIDType]
    end
    command = "powershell -Command \"Get-WinEvent -LogName #{event['Logfile']} -MaxEvents 50 | Where-Object {$_.RecordID  -eq \"#{event['RecordNumber']}\" } | Format-List Task,Opcode,Keywords\""
    result = %x[#{command}]

    additional_fields = Hash[*(result.split("\n").select {|i| !i.empty? }.map {|j| j.split(":") }.flatten.map {|k| k.strip })]

    event['opCode'] = additional_fields['Opcode']
    event['taskCategory'] = additional_fields['Task']
    event['keywords'] = additional_fields['Keywords']
  end
end