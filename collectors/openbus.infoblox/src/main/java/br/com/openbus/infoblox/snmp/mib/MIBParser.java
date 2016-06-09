package br.com.openbus.infoblox.snmp.mib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibLoaderException;
import net.percederberg.mibble.MibSymbol;
import net.percederberg.mibble.MibValue;
import net.percederberg.mibble.MibValueSymbol;
import net.percederberg.mibble.value.ObjectIdentifierValue;

public class MIBParser {

    private Mib mib;

    public MIBParser(File file) throws FileNotFoundException, MibLoaderException, IOException {
	this.mib = loadMib(file);
    }

    private Mib loadMib(File file) throws FileNotFoundException, MibLoaderException, IOException {

	MibLoader loader = new MibLoader();
	loader.addDir(file.getParentFile());
	return loader.load(file);
    }

    private ObjectIdentifierValue extractOid(MibSymbol symbol) {

	MibValue value;

	if (symbol instanceof MibValueSymbol) {
	    value = ((MibValueSymbol) symbol).getValue();
	    if (value instanceof ObjectIdentifierValue) {
		return (ObjectIdentifierValue) value;
	    }
	}
	return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap extractMibDescriptionsByName() {

	HashMap map = new HashMap();
	Iterator iter = mib.getAllSymbols().iterator();
	MibSymbol symbol;
	MibValue value = null;

	while (iter.hasNext()) {
	    symbol = (MibSymbol) iter.next();
	    value = extractOid(symbol);
	    boolean hasChildren = false;
	    int childCount = 0;
	    if (value != null) {

		if (symbol instanceof MibValueSymbol) {

		    childCount = ((MibValueSymbol) symbol).getChildCount();
		    hasChildren = childCount > 0 ? true : false;

		}
		map.put(symbol.getName(), new ObjectTypeDescription(symbol.getName(), value.toString(), hasChildren));
	    }
	}
	return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public HashMap extractMibDescriptionsByOID() {

	HashMap map = new HashMap();
	Iterator iter = mib.getAllSymbols().iterator();
	MibSymbol symbol;
	MibValue value = null;

	while (iter.hasNext()) {
	    symbol = (MibSymbol) iter.next();
	    value = extractOid(symbol);
	    boolean hasChildren = false;
	    int childCount = 0;
	    if (value != null) {

		if (symbol instanceof MibValueSymbol) {

		    childCount = ((MibValueSymbol) symbol).getChildCount();
		    hasChildren = childCount > 0 ? true : false;

		}
		map.put(value.toString(), new ObjectTypeDescription(symbol.getName(), value.toString(), hasChildren));
	    }
	}
	return map;
    }
}
