#!/usr/bin/env bash
# Copyright 2012 Citrix Systems, Inc. Licensed under the
# Apache License, Version 2.0 (the "License"); you may not use this
# file except in compliance with the License.  Citrix Systems, Inc.
# reserves all rights not expressly granted by the License.
# You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# 
# Automatically generated by addcopyright.py at 04/03/2012

source /root/func.sh

lock="biglock"
locked=$(getLockFile $lock)
if [ "$locked" != "1" ]
then
    exit 1
fi

usage() {
  printf "Usage: %s -[c|g|r] [-[a|d] <public interface>]\n" $(basename $0)  >&2
}

create_usage_rules () {
  iptables-save|grep "NETWORK_STATS_$gGateway -i $ethDev" > /dev/null
  if [ $? -gt 0 ]
  then 
    iptables -A NETWORK_STATS_$gGateway -i $ethDev -s ! zcidr > /dev/null
  fi
  iptables-save|grep "NETWORK_STATS_$gGateway -o $ethDev" > /dev/null
  if [ $? -gt 0 ]
  then 
    iptables -A NETWORK_STATS_$gGateway -o $ethDev -d ! zcidr > /dev/null
  fi
  return $?
}

get_usage () {
  iptables -L NETWORK_STATS_$gGateway -n -v -x | awk '$1 ~ /^[0-9]+$/ { printf "%s:", $2}'; > /dev/null
  if [ $? -gt 0 ]
  then
     printf $?
     return 1
  fi
}

reset_usage () {
  iptables -Z NETWORK_STATS_$gGateway > /dev/null
  if [ $? -gt 0  -a $? -ne 2 ]
  then
     return 1
  fi
}

#set -x

cflag=
gflag=
rflag=
iflag=
aflag=
dflag=

while getopts 'cgrl:d:z:' OPTION
do
  case $OPTION in
  c)	cflag=1
	;;
  g)	gflag=1
	;;
  r)	rflag=1
	;;
  l)    lflag=1
        gGateway="$OPTARG"
        ;;
  d)    dflag=1
        ethDev="$OPTARG"
        ;;
  z)    zflag=1
        zcidr="$OPTARG"
        ;;
  i)    #Do nothing, since it's parameter for host script
        ;;
  ?)	usage
        unlock_exit 2 $lock $locked
	;;
  esac
done

if [ "$cflag" == "1" ] 
then
  create_usage_rules  
  unlock_exit $? $lock $locked
fi

if [ "$gflag" == "1" ] 
then
  get_usage 
  unlock_exit $? $lock $locked
fi

if [ "$rflag" == "1" ] 
then
  reset_usage  
  unlock_exit $? $lock $locked
fi


unlock_exit 0 $lock $locked
