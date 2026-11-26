package com.lostmypillow.jd2.accounts;

import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.JsonMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountsController {
    public final AccountsClient client;

    public AccountsController(AccountsClient client) {
        this.client = client;
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Boolean> addAccount(
            @RequestParam("premiumHoster") String premiumHoster,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(client.addAccount(premiumHoster, username, password).data());
    }

    @PostMapping("/disableAccounts")
    public ResponseEntity<Boolean> disableAccounts(@RequestParam("ids") String ids) {
        return ResponseEntity.ok(client.disableAccounts(ids).data());
    }

    @PostMapping("/enableAccounts")
    public ResponseEntity<Boolean> enableAccounts(@RequestParam("ids") String ids) {
        return ResponseEntity.ok(client.enableAccounts(ids).data());
    }

    @GetMapping("/getAccountInfo")
    public ResponseEntity<Account> getAccountInfo(@RequestParam("id") long id) {
        return ResponseEntity.ok(client.getAccountInfo(id).data());
    }

    @GetMapping("/getPremiumHosterUrl")
    public ResponseEntity<String> getPremiumHosterUrl(@RequestParam("hoster") String hoster) {
        return ResponseEntity.ok(client.getPremiumHosterUrl(hoster).data());
    }

    @GetMapping("/listPremiumHoster")
    public ResponseEntity<List<String>> listPremiumHoster() {
        return ResponseEntity.ok(client.listPremiumHoster().data());
    }

    @GetMapping("/listPremiumHosterUrls")
    public ResponseEntity<JsonMap> listPremiumHosterUrls() {
        return ResponseEntity.ok(client.listPremiumHosterUrls().data());
    }

    @GetMapping("/premiumHosterIcon")
    public ResponseEntity<Void> premiumHosterIcon(@RequestParam("premiumHoster") String premiumHoster) {
        client.premiumHosterIcon(premiumHoster);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/queryAccounts")
    public ResponseEntity<List<Account>> queryAccounts(@RequestParam("query") String query) {
        return ResponseEntity.ok(client.queryAccounts(query).data());
    }

    @PostMapping("/removeAccounts")
    public ResponseEntity<Boolean> removeAccounts(@RequestParam("ids") String ids) {
        return ResponseEntity.ok(client.removeAccounts(ids).data());
    }

    @PostMapping("/setEnabledState")
    public ResponseEntity<Boolean> setEnabledState(
            @RequestParam("enabled") boolean enabled,
            @RequestParam("ids") String ids
    ) {
        return ResponseEntity.ok(client.setEnabledState(enabled, ids).data());
    }

    @PostMapping("/updateAccount")
    public ResponseEntity<Boolean> updateAccount(
            @RequestParam("accountId") Long accountId,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(client.updateAccount(accountId, username, password).data());
    }
}

