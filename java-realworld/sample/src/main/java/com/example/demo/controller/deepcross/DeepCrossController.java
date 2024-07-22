package com.example.demo.controller.deepcross;

import com.example.demo.controller.utils.DummyUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeepCrossController {
    @GetMapping({"/xss/safe", "/xss/no-cross"})
    public ResponseEntity<String> noDeepCross(@RequestParam(required = false) String body) {
        if (body == null) {
            return ResponseEntity.ok("No input, try <a href='/xss/no-cross?body=hello-world'>here</a>");
        }
        ResponseEntity<String> resp = ResponseEntity.ok(body);
        return resp;
    }

    @GetMapping({"/xss/unsafe1", "/xss/cross-method"})
    public ResponseEntity<String> CrossMethod(@RequestParam String body) {
        return DeepCrossController.directWrite(body);
    }

    private static ResponseEntity<String> directWrite(String body) {
        ResponseEntity<String> resp = ResponseEntity.ok(body);
        return resp;
    }

    @GetMapping({"/xss/unsafe2", "/xss/cross-other-method"})
    public ResponseEntity<String> CrossMethod3(@RequestParam String body) {
        return directWrite(DummyUtil.nothing(body));
    }

    @GetMapping({"/xss/unsafe3", "/xss/cross-other-filter"})
    public ResponseEntity<String> CrossMethod4(@RequestParam String body) {
        return directWrite(DummyUtil.filterXSS(body));
    }
}

