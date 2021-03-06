// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
package com.cloud.api.response;

import com.cloud.utils.IdentityProxy;
import com.cloud.serializer.Param;
import com.google.gson.annotations.SerializedName;

/*
 * This is the generic response for all types of System VMs (SSVM, consoleproxy, domain routers(router, LB, DHCP))
 */
public class SystemVmInstanceResponse extends BaseResponse {
    @SerializedName("id") @Param(description="the ID of the system VM")
    private IdentityProxy id = new IdentityProxy("vm_instance");

    @SerializedName("systemvmtype") @Param(description="the system VM type")
    private String systemVmType;

    @SerializedName("name") @Param(description="the name of the system VM")
    private String name;

    @SerializedName("hostid") @Param(description="the host ID for the system VM")
    private IdentityProxy hostId = new IdentityProxy("host");

    @SerializedName("state") @Param(description="the state of the system VM")
    private String state;
    
    @SerializedName("role") @Param(description="the role of the system VM")
    private String role;
    
    
    public Long getObjectId() {
    	return getId();
    }

    public Long getId() {
        return id.getValue();
    }

    public void setId(Long id) {
        this.id.setValue(id);
    }

    public String getSystemVmType() {
        return systemVmType;
    }

    public void setSystemVmType(String systemVmType) {
        this.systemVmType = systemVmType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHostId() {
        return hostId.getValue();
    }

    public void setHostId(Long hostId) {
        this.hostId.setValue(hostId);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }    

}
