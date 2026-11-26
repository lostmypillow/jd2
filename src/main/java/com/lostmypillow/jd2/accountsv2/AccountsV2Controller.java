package com.lostmypillow.jd2.accountsv2;

import com.lostmypillow.jd2.constants.Type;
import com.lostmypillow.jd2.structure.Account;
import com.lostmypillow.jd2.structure.BasicAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/accountsV2")
public class AccountsV2Controller {
    public final AccountsV2Client client;

    public AccountsV2Controller(AccountsV2Client client) {
        this.client = client;
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Void> addAccount(
            @RequestParam("premiumHoster") String premiumHoster,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        client.addAccount(premiumHoster, username, password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addBasicAuth")
    public ResponseEntity<Long> addBasicAuth(
            @RequestParam("type") Type type,
            @RequestParam("hostmask") String hostmask,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(client.addBasicAuth(type, hostmask, username, password).data());
    }

    @PostMapping("/disableAccounts")
    public ResponseEntity<Void> disableAccounts(@RequestParam("ids") String ids) {
        client.disableAccounts(ids);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/enableAccounts")
    public ResponseEntity<Void> enableAccounts(@RequestParam("ids") String ids) {
        client.enableAccounts(ids);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getPremiumHosterUrl")
    public ResponseEntity<String> getPremiumHosterUrl(@RequestParam("hoster") String hoster) {
        return ResponseEntity.ok(client.getPremiumHosterUrl(hoster).data());
    }

    @PostMapping("/listAccounts")
    public ResponseEntity<List<Account>> listAccounts(@RequestParam("query") String query) {
        return ResponseEntity.ok(client.listAccounts(query).data());
    }

    @GetMapping("/listBasicAuth")
    public ResponseEntity<List<BasicAuthentication>> listBasicAuth() {
        return ResponseEntity.ok(client.listBasicAuth().data());
    }

    @GetMapping("/listPremiumHoster")
    public ResponseEntity<List<String>> listPremiumHoster() {
        return ResponseEntity.ok(client.listPremiumHoster().data());
    }

    @GetMapping("/listPremiumHosterUrls")
    public ResponseEntity<Map<String, String>> listPremiumHosterUrls() {
        return ResponseEntity.ok(client.listPremiumHosterUrls().data());
    }

    @PostMapping("/refreshAccounts")
    public ResponseEntity<Void> refreshAccounts(@RequestParam("ids") String ids) {
        client.refreshAccounts(ids);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeAccounts")
    public ResponseEntity<Void> removeAccounts(@RequestParam("ids") String ids) {
        client.removeAccounts(ids);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeBasicAuths")
    public ResponseEntity<Boolean> removeBasicAuths(@RequestParam("ids") String ids) {
        return ResponseEntity.ok(client.removeBasicAuths(ids).data());
    }

    @PostMapping("/setUserNameAndPassword")
    public ResponseEntity<Boolean> setUserNameAndPassword(
            @RequestParam("accountId") long accountId,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        return ResponseEntity.ok(client.setUserNameAndPassword(accountId, username, password).data());
    }

    @PostMapping("/updateBasicAuth")
    public ResponseEntity<Boolean> updateBasicAuth(@RequestParam("updatedEntry") String updatedEntry) {
        return ResponseEntity.ok(client.updateBasicAuth(updatedEntry).data());
    }
}

