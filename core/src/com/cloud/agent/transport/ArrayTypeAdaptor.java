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
package com.cloud.agent.transport;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.cloud.agent.api.Command;
import com.cloud.utils.exception.CloudRuntimeException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ArrayTypeAdaptor<T> implements JsonDeserializer<T[]>, JsonSerializer<T[]> {

    protected Gson              _gson = null;


    private static final String s_pkg = Command.class.getPackage().getName() + ".";

    public ArrayTypeAdaptor() {
    }

    public void initGson(Gson gson) {
        _gson = gson;
    }

    @Override
    public JsonElement serialize(T[] src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray array = new JsonArray();
        for (T cmd : src) {
            JsonObject obj = new JsonObject();
            obj.add(cmd.getClass().getName().substring(s_pkg.length()), _gson.toJsonTree(cmd));
            array.add(obj);
        }

        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        Iterator<JsonElement> it = array.iterator();
        ArrayList<T> cmds = new ArrayList<T>();
        while (it.hasNext()) {
            JsonObject element = (JsonObject)it.next();
            Map.Entry<String, JsonElement> entry = element.entrySet().iterator().next();

            String name = s_pkg + entry.getKey();
            Class<?> clazz;
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException e) {
                throw new CloudRuntimeException("can't find " + name);
            }
            T cmd = (T)_gson.fromJson(entry.getValue(), clazz);
            cmds.add(cmd);
        }
        Class<?> type = ((Class<?>)typeOfT).getComponentType();
        T[] ts = (T[])Array.newInstance(type, cmds.size());
        return cmds.toArray(ts);
    }
}