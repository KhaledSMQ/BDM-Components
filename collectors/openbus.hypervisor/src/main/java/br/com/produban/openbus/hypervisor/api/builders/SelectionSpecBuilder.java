package br.com.produban.openbus.hypervisor.api.builders;

import com.vmware.vim25.SelectionSpec;

public class SelectionSpecBuilder extends SelectionSpec {
	public SelectionSpecBuilder name(final String name) {
		this.setName(name);
		return this;
	}
}
