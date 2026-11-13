package com.lostmypillow.jd2.plugins;

import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.Plugin;
import com.lostmypillow.jd2.structure.PluginConfigEntry;
import com.lostmypillow.jd2.structure.PluginsQuery;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

@HttpExchange("/plugins")
public interface PluginsClient {
    /**
     * Gets plugin config value by interfaceName, displayName, and key
     *
     * @param interfaceName interface name
     * @param displayName display name
     * @param key config key
     * @return a {@link Jd2Response} containing the plugin config value
     */
    @GetExchange("/get")
    Jd2Response<Object> get(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("displayName") String displayName,
            @RequestParam("key") String key
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Gets all plugin regex patterns mapped by plugin name
     *
     * @return a {@link Jd2Response} containing a {@link Map} of plugin names to a {@link List} of regex {@link String}s
     */
    @GetExchange("/getAllPluginRegex")
    Jd2Response<Map<String, List<String>>> getAllPluginRegex();

    /**
     * Gets plugin regex patterns matching a given URL
     *
     * @param url URL to check
     * @return a {@link Jd2Response} containing a {@link List} of regex {@link String}s
     */
    @GetExchange("/getPluginRegex")
    Jd2Response<List<String>> getPluginRegex(@RequestParam("URL") String url);

    /**
     * Lists available plugins matching the query
     *
     * @param query a stringified {@link PluginsQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link Plugin}s
     */
    @PostExchange("/list")
    Jd2Response<List<Plugin>> list(@RequestParam("query") String query);

    /**
     * Queries plugin config entries
     *
     * @param query a stringified {@link AdvancedConfigQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link PluginConfigEntry}
     */
    @PostExchange("/query")
    Jd2Response<List<PluginConfigEntry>> query(@RequestParam("query") String query);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Resets plugin config value to default by interfaceName, displayName, and key
     *
     * @param interfaceName interface name
     * @param displayName display name
     * @param key config key
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/reset")
    Jd2Response<Boolean> reset(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("displayName") String displayName,
            @RequestParam("key") String key
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter
    // ResponseCode 400 (Bad Request); Type:INVALID_VALUE

    /**
     * Sets plugin config value by interfaceName, displayName, and key
     *
     * @param interfaceName interface name
     * @param displayName display name
     * @param key config key
     * @param newValue new value to set
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/set")
    Jd2Response<Boolean> set(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("displayName") String displayName,
            @RequestParam("key") String key,
            @RequestParam("newValue") Object newValue
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter
    // ResponseCode 400 (Bad Request); Type:INVALID_VALUE
}

