package org.nico.business.impl.manager;

import org.nico.business.contract.manager.EnumManager;
import org.nico.business.impl.AbstractManager;
import org.nico.model.enums.Role;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("enumManager")
public class EnumManagerImpl extends AbstractManager implements EnumManager {

    @Override
    public List<String> getEnumRoleStringValues (){
        Role[] listRole = Role.values();
        List<String> listRoleStringValues = new ArrayList<>();

        for (Role enumRole : listRole){
            listRoleStringValues.add(enumRole.getParam());
        }

        return  listRoleStringValues;
    }
}
