package com.lostmypillow.jd2.config;

import com.lostmypillow.jd2.structure.AdvancedConfigAPIEntry;
import com.lostmypillow.jd2.structure.AdvancedConfigQuery;
import com.lostmypillow.jd2.structure.EnumOption;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/config")
public interface ConfigClient {
    /**
     * Gets value from interface by key
     *
     * @param interfaceName interface name
     * @param storage storage name
     * @param key config key
     * @return a {@link Jd2Response} containing the config value
     */
    @GetExchange("/get")
    Jd2Response<Object> get(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key
    );

    /**
     * Gets default value from interface by key
     *
     * @param interfaceName interface name
     * @param storage storage name
     * @param key config key
     * @return a {@link Jd2Response} containing the default config value
     */
    @GetExchange("/getDefault")
    Jd2Response<Object> getDefault(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key
    );

    /**
     * Lists all available config entries
     *
     * @return a {@link Jd2Response} containing a {@link List} of {@link AdvancedConfigAPIEntry}
     */
    @GetExchange("/list")
    Jd2Response<List<AdvancedConfigAPIEntry>> list();

    /**
     * Lists entries based on the pattern regex
     *
     * @param pattern regex pattern
     * @param returnDescription whether to return description
     * @param returnValues whether to return values
     * @param returnDefaultValues whether to return default values
     * @param returnEnumInfo whether to return enum info
     * @return a {@link Jd2Response} containing a {@link List} of {@link AdvancedConfigAPIEntry}
     */
    @GetExchange("/list")
    Jd2Response<List<AdvancedConfigAPIEntry>> list(
            @RequestParam("pattern") String pattern,
            @RequestParam("returnDescription") boolean returnDescription,
            @RequestParam("returnValues") boolean returnValues,
            @RequestParam("returnDefaultValues") boolean returnDefaultValues,
            @RequestParam("returnEnumInfo") boolean returnEnumInfo
    );

    /**
     * @deprecated DEPRECATED! list entries based on the pattern regex. This method will be removed soon. DO NOT USE IT!
     */
    @Deprecated
    @GetExchange("/list")
    Jd2Response<List<AdvancedConfigAPIEntry>> list(
            @RequestParam("pattern") String pattern,
            @RequestParam("returnDescription") boolean returnDescription,
            @RequestParam("returnValues") boolean returnValues,
            @RequestParam("returnDefaultValues") boolean returnDefaultValues
    );

    /**
     * Lists all possible enum values
     *
     * @param type enum type
     * @return a {@link Jd2Response} containing a {@link List} of {@link EnumOption}
     */
    @GetExchange("/listEnum")
    Jd2Response<List<EnumOption>> listEnum(@RequestParam("type") String type);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Queries config entries
     *
     * @param query a stringified {@link AdvancedConfigQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link AdvancedConfigAPIEntry}
     */
    @PostExchange("/query")
    Jd2Response<List<AdvancedConfigAPIEntry>> query(@RequestParam("query") String query);

    /**
     * Resets interface by key to its default value
     *
     * @param interfaceName interface name
     * @param storage storage name
     * @param key config key
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/reset")
    Jd2Response<Boolean> reset(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key
    );

    /**
     * Sets value to interface by key
     *
     * @param interfaceName interface name
     * @param storage storage name
     * @param key config key
     * @param value value to set
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/set")
    Jd2Response<Boolean> set(
            @RequestParam("interfaceName") String interfaceName,
            @RequestParam("storage") String storage,
            @RequestParam("key") String key,
            @RequestParam("value") Object value
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:INVALID_VALUE
}

