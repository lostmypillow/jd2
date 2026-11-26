package com.lostmypillow.jd2.accountsv2;

import com.lostmypillow.jd2.constants.Type;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.AccountQuery;
import com.lostmypillow.jd2.structure.BasicAuthentication;
import com.lostmypillow.jd2.structure.Jd2Response;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;
import java.util.Map;

@HttpExchange("/accountsV2")
public interface AccountsV2Client {
    @PostExchange("/addAccount")
    void addAccount(
            @RequestParam("premiumHoster") String premiumHoster,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

    @PostExchange("/addBasicAuth")
    Jd2Response<Long> addBasicAuth(
            @RequestParam("type") Type type,
            @RequestParam("hostmask") String hostmask,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    /**
     * Disables accounts
     *
     * @param ids a stringified {@code long[]} of account IDs
     */
    @PostExchange("/disableAccounts")
    void disableAccounts(@RequestParam("ids") String ids);

    /**
     * Enables accounts
     *
     * @param ids a stringified {@code long[]} of account IDs
     */
    @PostExchange("/enableAccounts")
    void enableAccounts(@RequestParam("ids") String ids);

    @GetExchange("/getPremiumHosterUrl")
    Jd2Response<String> getPremiumHosterUrl(@RequestParam("hoster") String hoster);

    /**
     * Lists accounts
     *
     * @param query a stringified {@link AccountQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link Account}
     */
    @PostExchange("/listAccounts")
    Jd2Response<List<Account>> listAccounts(@RequestParam("query") String query);

    @GetExchange("/listBasicAuth")
    Jd2Response<List<BasicAuthentication>> listBasicAuth();

    @GetExchange("/listPremiumHoster")
    Jd2Response<List<String>> listPremiumHoster();

    @GetExchange("/listPremiumHosterUrls")
    Jd2Response<Map<String, String>> listPremiumHosterUrls();

    /**
     * Refreshes accounts
     *
     * @param ids a stringified {@code long[]} of account IDs
     */
    @PostExchange("/refreshAccounts")
    void refreshAccounts(@RequestParam("ids") String ids);

    /**
     * Removes accounts
     *
     * @param ids a stringified {@code long[]} of account IDs
     */
    @PostExchange("/removeAccounts")
    void removeAccounts(@RequestParam("ids") String ids);

    /**
     * Removes basic auth entries
     *
     * @param ids a stringified {@code long[]} of basic auth IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/removeBasicAuths")
    Jd2Response<Boolean> removeBasicAuths(@RequestParam("ids") String ids);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter

    @PostExchange("/setUserNameAndPassword")
    Jd2Response<Boolean> setUserNameAndPassword(
            @RequestParam("accountId") long accountId,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

    /**
     * Updates a basic auth entry
     *
     * @param updatedEntry a stringified {@link BasicAuthentication} object
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/updateBasicAuth")
    Jd2Response<Boolean> updateBasicAuth(@RequestParam("updatedEntry") String updatedEntry);
    // Possible Error(s) ResponseCode 400 (Bad Request); Type:BAD_PARAMETERS; Description: Bad Parameter
}

