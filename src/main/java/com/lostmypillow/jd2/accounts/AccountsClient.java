package com.lostmypillow.jd2.accounts;

import com.lostmypillow.jd2.structure.APIQuery;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.Jd2Response;
import com.lostmypillow.jd2.structure.JsonMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/accounts")
public interface AccountsClient {
    @PostExchange("/addAccount")
    Jd2Response<Boolean> addAccount(
            @RequestParam("premiumHoster") String premiumHoster,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

    /**
     * Disables accounts
     *
     * @param ids a stringified {@link List} of account IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/disableAccounts")
    Jd2Response<Boolean> disableAccounts(@RequestParam("ids") String ids);

    /**
     * Enables accounts
     *
     * @param ids a stringified {@link List} of account IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/enableAccounts")
    Jd2Response<Boolean> enableAccounts(@RequestParam("ids") String ids);

    @GetExchange("/getAccountInfo")
    Jd2Response<Account> getAccountInfo(@RequestParam("id") long id);

    @GetExchange("/getPremiumHosterUrl")
    Jd2Response<String> getPremiumHosterUrl(@RequestParam("hoster") String hoster);

    @GetExchange("/listPremiumHoster")
    Jd2Response<List<String>> listPremiumHoster();

    @GetExchange("/listPremiumHosterUrls")
    Jd2Response<JsonMap> listPremiumHosterUrls();

    @GetExchange("/premiumHosterIcon")
    void premiumHosterIcon(@RequestParam("premiumHoster") String premiumHoster);
    // Possible Error(s) ResponseCode 500 (Internal Server Error); Type:INTERNAL_SERVER_ERROR

    /**
     * Queries accounts
     *
     * @param query a stringified {@link APIQuery} object
     * @return a {@link Jd2Response} containing a {@link List} of {@link Account}
     */
    @PostExchange("/queryAccounts")
    Jd2Response<List<Account>> queryAccounts(@RequestParam("query") String query);

    /**
     * Removes accounts
     *
     * @param ids a stringified {@code Long[]} of account IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/removeAccounts")
    Jd2Response<Boolean> removeAccounts(@RequestParam("ids") String ids);

    /**
     * Sets enabled state for accounts
     *
     * @param enabled enabled flag
     * @param ids a stringified {@code Long[]} of account IDs
     * @return a {@link Jd2Response} containing a {@link Boolean}
     */
    @PostExchange("/setEnabledState")
    Jd2Response<Boolean> setEnabledState(
            @RequestParam("enabled") boolean enabled,
            @RequestParam("ids") String ids
    );

    @PostExchange("/updateAccount")
    Jd2Response<Boolean> updateAccount(
            @RequestParam("accountId") Long accountId,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );
}

