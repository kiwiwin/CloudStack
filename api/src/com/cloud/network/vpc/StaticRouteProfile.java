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
package com.cloud.network.vpc;

/**
 * @author Alena Prokharchyk
 */
public class StaticRouteProfile implements StaticRoute{
    private long id;
    private String cidr;
    private long accountId;
    private long domainId;
    private long gatewayId;
    private StaticRoute.State state;
    private long vpcId;
    String vlanTag;
    String gateway;
    String netmask;
    String ipAddress;
    

    public StaticRouteProfile(StaticRoute staticRoute, PrivateGateway gateway) {
        this.id = staticRoute.getId();
        this.cidr = staticRoute.getCidr();
        this.accountId = staticRoute.getAccountId();
        this.domainId = staticRoute.getDomainId();
        this.gatewayId = staticRoute.getVpcGatewayId();
        this.state = staticRoute.getState();
        this.vpcId = staticRoute.getVpcId();
        this.vlanTag = gateway.getVlanTag();
        this.gateway = gateway.getGateway();
        this.netmask = gateway.getNetmask();
        this.ipAddress = gateway.getIp4Address();
    }

    @Override
    public long getAccountId() {
        return accountId;
    }

    @Override
    public long getDomainId() {
        return domainId;
    }

    @Override
    public long getVpcGatewayId() {
        return gatewayId;
    }

    @Override
    public String getCidr() {
        return cidr;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public Long getVpcId() {
       return vpcId;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getVlanTag() {
        return vlanTag;
    }
    
    public String getIp4Address() {
        return ipAddress;
    }

    public String getGateway() {
        return gateway;
    }

    public String getNetmask() {
        return netmask;
    }
    
}
