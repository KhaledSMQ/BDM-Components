/**
 * ManagementKeyCertificatePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.produban.openbus.f5.api;

public interface ManagementKeyCertificatePortType extends java.rmi.Remote {

    /**
     * Checks to see if the device supports FIPS security.
     */
    public boolean is_fips_available(ManagementKeyCertificateManagementModeType mode) throws java.rmi.RemoteException;

    /**
     * Gets the list of all installed keys and their information.
     */
    public ManagementKeyCertificateKeyInformation[] get_key_list(ManagementKeyCertificateManagementModeType mode) throws java.rmi.RemoteException;

    /**
     * Generates the specified keys.
     */
    public void key_generate(ManagementKeyCertificateManagementModeType mode, ManagementKeyCertificateKey[] keys, ManagementKeyCertificateX509Data[] x509_data, boolean create_optional_cert_csr, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified keys from the given PEM-formatted
     * data.
     */
    public void key_import_from_pem(ManagementKeyCertificateManagementModeType mode, String[] key_ids, String[] pem_data, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified keys from the given files.
     */
    public void key_import_from_file(ManagementKeyCertificateManagementModeType mode, String[] key_ids, String[] file_names, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Exports the specified keys to PEM-formatted data.
     */
    public String[] key_export_to_pem(ManagementKeyCertificateManagementModeType mode, String[] key_ids) throws java.rmi.RemoteException;

    /**
     * Exports the specified keys to the given files.
     */
    public void key_export_to_file(ManagementKeyCertificateManagementModeType mode, String[] key_ids, String[] file_names, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Deletes/uninstalls the specified keys.
     */
    public void key_delete(ManagementKeyCertificateManagementModeType mode, String[] key_ids) throws java.rmi.RemoteException;

    /**
     * Converts the specified keys to FIPS-enabled keys.
     */
    public void key_to_fips(ManagementKeyCertificateManagementModeType mode, String[] key_ids) throws java.rmi.RemoteException;

    /**
     * Gets the list of all installed certificates and their information.
     * If there's 
     *  a certificate bundle, only the first certificate in the bundle is
     * returned, and
     *  and is_bundle flag will be set to true for the correponding bundle
     * file name.
     */
    public ManagementKeyCertificateCertificateInformation[] get_certificate_list(ManagementKeyCertificateManagementModeType mode) throws java.rmi.RemoteException;

    /**
     * Gets the list of all certificates bundled in the certificate
     * files as 
     *  specified by the file_names.  Each file_name will contain multiple
     * certficates.
     *  Note: only call this method when the results of get_certificate_list
     * indicate
     *  that there are multiple certificates bundled in a particular file.
     */
    public ManagementKeyCertificateCertificateDetail[][] get_certificate_bundle(ManagementKeyCertificateManagementModeType mode, String[] file_names) throws java.rmi.RemoteException;

    /**
     * Gets the list of all certificate subject alternative names
     * bundled in the certificate files as
     *  specified by the file_names.  Each file_name will contain multiple
     * certficate subject alternative
     *  names.
     *  Note: only call this method when the results of get_certificate_list
     * indicate
     *  that there are multiple certificats bundled in a particular file.
     */
    public String[][] get_certificate_subject_alternative_name_bundle(ManagementKeyCertificateManagementModeType mode, String[] file_names) throws java.rmi.RemoteException;

    /**
     * Adds certificates identified by "pem_data" to the certificate
     * bundles, which are presumed
     *  to exist already. Each of the original certificate bundle can theoretically
     * be a normal
     *  certificate, i.e. a certificate bundle of one. After the add operation,
     * the bundles will
     *  contain more than one certificate.
     */
    public void certificate_add_pem_to_bundle(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, String[] pem_data) throws java.rmi.RemoteException;

    /**
     * Adds certificates identified by "certificate_files" to the
     * certificate bundles, which are 
     *  presumed to exist already. Each of the original certificate bundle
     * can theoretically be a 
     *  normal certificate, i.e. a certificate bundle of one. After the add
     * operation, the bundles 
     *  will contain more than one certificate.
     */
    public void certificate_add_file_to_bundle(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, String[] certificate_files) throws java.rmi.RemoteException;

    /**
     * Deletes certificates, identified by their subject's X509 data,
     * from the certificate bundles.
     *  If the last certificate has been deleted from the bundle, the certificate
     * file will 
     *  automatically be deleted.
     */
    public void certificate_delete_from_bundle(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, ManagementKeyCertificateX509Data[] x509_data) throws java.rmi.RemoteException;

    /**
     * Generates the specified certificates.  This assumes that each
     * of the associated keys,
     *  having the same identification as each certificate, has already been
     * created.
     */
    public void certificate_generate(ManagementKeyCertificateManagementModeType mode, ManagementKeyCertificateCertificate[] certs, ManagementKeyCertificateX509Data[] x509_data, long[] lifetime_days, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified certificates from the given
     * PEM-formatted data.
     */
    public void certificate_import_from_pem(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, String[] pem_data, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified certificates from the given
     * files.
     */
    public void certificate_import_from_file(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, String[] file_names, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Exports the specified certificates to PEM-formatted data.
     */
    public String[] certificate_export_to_pem(ManagementKeyCertificateManagementModeType mode, String[] cert_ids) throws java.rmi.RemoteException;

    /**
     * Exports the specified certificates to the given files.
     */
    public void certificate_export_to_file(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, String[] file_names, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Deletes/uninstalls the specified certificates.
     */
    public void certificate_delete(ManagementKeyCertificateManagementModeType mode, String[] cert_ids) throws java.rmi.RemoteException;

    /**
     * Gets the validity of the specified certificates.
     */
    public ManagementKeyCertificateValidityType[] certificate_check_validity(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, long[] watermark_days) throws java.rmi.RemoteException;

    /**
     * Binds/associates the specified keys and certificates.
     */
    public void certificate_bind(ManagementKeyCertificateManagementModeType mode, String[] cert_ids, String[] key_ids) throws java.rmi.RemoteException;

    /**
     * Gets the list of all CSRs and their information.
     */
    public ManagementKeyCertificateCertificateRequestInformation[] get_certificate_request_list(ManagementKeyCertificateManagementModeType mode) throws java.rmi.RemoteException;

    /**
     * Generates the specified certificate signing requests.  This
     * assumes that each of the 
     *  associated keys, having the same identification as each certificate
     * request, has 
     *  already been created.
     */
    public void certificate_request_generate(ManagementKeyCertificateManagementModeType mode, ManagementKeyCertificateCertificateRequest[] csrs, ManagementKeyCertificateX509Data[] x509_data, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified certificate requests from the
     * given PEM-formatted data.
     */
    public void certificate_request_import_from_pem(ManagementKeyCertificateManagementModeType mode, String[] csr_ids, String[] pem_data, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified certificate requests from the
     * given files.
     */
    public void certificate_request_import_from_file(ManagementKeyCertificateManagementModeType mode, String[] csr_ids, String[] file_names, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Exports the specified certificate requests to PEM-formatted
     * data.
     */
    public String[] certificate_request_export_to_pem(ManagementKeyCertificateManagementModeType mode, String[] csr_ids) throws java.rmi.RemoteException;

    /**
     * Exports the specified certificate requests to the given files.
     */
    public void certificate_request_export_to_file(ManagementKeyCertificateManagementModeType mode, String[] csr_ids, String[] file_names, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Deletes the specified CSRs.
     */
    public void certificate_request_delete(ManagementKeyCertificateManagementModeType mode, String[] csr_ids) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified pkcs12 (Public Key
     *  Cryptography Standard #12) information from the given
     *  files. This includes keys and certificates, and each file
     *  is optionally password-encrypted.
     */
    public void pkcs12_import_from_file(ManagementKeyCertificateManagementModeType mode, String[] ids, String[] file_names, String[] passwords, boolean overwrite) throws java.rmi.RemoteException;

    /**
     * Exports all currently installed keys and certificates into
     * the specified archive file.
     *  The archive file is a .tgz file that will contain all keys and certificates.
     */
    public void export_all_to_archive_file(ManagementKeyCertificateManagementModeType mode, String archive_location, String archive_name) throws java.rmi.RemoteException;

    /**
     * Imports/installs all keys and certificates from the specified
     * archive file.
     *  The archive file should be a .tgz file that contains all keys and
     * certificates.
     */
    public void import_all_from_archive_file(ManagementKeyCertificateManagementModeType mode, String archive_location, String archive_name) throws java.rmi.RemoteException;

    /**
     * Exports all currently installed keys and certificates into
     * the returned archive stream.
     *  The returned archive stream is basically the contents of a .tgz file
     * that contains 
     *  all keys and certificates.
     */
    public byte[] export_all_to_archive_stream(ManagementKeyCertificateManagementModeType mode) throws java.rmi.RemoteException;

    /**
     * Imports/installs all keys and certificates from the incoming
     * archive stream.
     *  The archive stream should be the contents of a .tgz file that contains
     * all 
     *  keys and certificates.
     */
    public void import_all_from_archive_stream(ManagementKeyCertificateManagementModeType mode, byte[] archive_stream) throws java.rmi.RemoteException;

    /**
     * Exports the specified keys and certificates into the specified
     * archive file.
     *  The archive file is a .tgz file that will contain only the specified
     * keys and 
     *  certificates that have been exported.
     */
    public void export_to_archive_file(ManagementKeyCertificateManagementModeType mode, String archive_location, String archive_name, String[] keys, String[] certs) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified keys and certificates from the
     * specified archive file.
     *  The archive file should be a .tgz file that may contain more keys
     * and certificates 
     *  than what will be imported/installed.
     */
    public void import_from_archive_file(ManagementKeyCertificateManagementModeType mode, String archive_location, String archive_name, String[] keys, String[] certs) throws java.rmi.RemoteException;

    /**
     * Exports the specified keys and certificates into the returned
     * archive stream.
     *  The returned archive stream is basically the contents of a .tgz file
     * that contains 
     *  the exported keys and certificates.
     */
    public byte[] export_to_archive_stream(ManagementKeyCertificateManagementModeType mode, String[] keys, String[] certs) throws java.rmi.RemoteException;

    /**
     * Imports/installs the specified keys and certificates from the
     * incoming archive stream.
     *  The archive stream should be the contents of a .tgz file that may
     * contain more keys 
     *  and certificates than what will be imported/installed.
     */
    public void import_from_archive_stream(ManagementKeyCertificateManagementModeType mode, byte[] archive_stream, String[] keys, String[] certs) throws java.rmi.RemoteException;

    /**
     * Gets a list of valid key sizes for specified key types.
     * 
     *  The valid key sizes are bit-lengths of keys that are
     *  supported by the system.  For example, 1024 and 2048 mean
     *  128-byte and 256-byte RSA key sizes.  These sizes are used
     *  when generating or importing a key.
     */
    public long[][] get_valid_key_sizes(ManagementKeyCertificateManagementModeType mode, ManagementKeyCertificateKeyType[] key_types, boolean[] fips) throws java.rmi.RemoteException;

    /**
     * Gets the version information for this interface.
     */
    public String get_version() throws java.rmi.RemoteException;
}
