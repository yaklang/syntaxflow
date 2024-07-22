package com.example.demo.controller.deepcross;

import com.example.demo.controller.utils.DummyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeepCrossController {
    @GetMapping({"/xss/direct/1", "/xss/no-cross/1"})
    public ResponseEntity<String> noDeepCross(@RequestParam(required = false) String body) {
        if (body == null) {
            return ResponseEntity.ok("No input, try <a href='/xss/no-cross?body=hello-world'>here</a>");
        }
        ResponseEntity<String> resp = ResponseEntity.ok(body);
        return resp;
    }

    @GetMapping({"/xss/direct/2", "/xss/no-cross/2"})
    public ResponseEntity<String> noDeepCross1(@RequestParam(required = false) String body) {
        if (body == null) {
            return ResponseEntity.ok("No input, try <a href='/xss/no-cross?body=hello-world'>here</a>");
        }
        ResponseEntity<String> resp = ResponseEntity.ok().body(body);
        return resp;
    }

    @GetMapping({"/xss/direct/3", "/xss/no-cross/3"})
    public ResponseEntity<String> noDeepCross2(@RequestParam(required = false) String body) {
        if (body == null) {
            return ResponseEntity.ok("No input, try <a href='/xss/no-cross?body=hello-world'>here</a>");
        }
        ResponseEntity<String> resp = new ResponseEntity(body, HttpStatus.OK);
        return resp;
    }

    @GetMapping({"/xss/direct/4", "/xss/no-cross/4"})
    public ResponseEntity<String> noDeepCross4(@RequestParam(required = false) String body) {
        if (body == null) {
            return ResponseEntity.ok("No input, try <a href='/xss/no-cross?body=hello-world'>here</a>");
        }
        ResponseEntity<String> resp = new ResponseEntity(body, HttpStatus.OK);
        return resp;
    }

    @GetMapping({"/xss/direct/5"})
    public ResponseEntity<String> noDeepCross5(@RequestParam(required = false) String body) {
        if (body == null) {
            return ResponseEntity.ok("No input, try <a href='/xss/no-cross?body=hello-world'>here</a>");
        }
        body = "Pre Handle" + body;
        body = body.replaceAll("Hello", "---Hello---");
        body += "\n\nSigned by DeepCrossController";
        ResponseEntity<String> resp = new ResponseEntity(body, HttpStatus.OK);
        return resp;
    }

    @GetMapping({"/xss/direct/6"})
    public ResponseEntity<String> noDeepCross6(@RequestParam(required = false) String body) {
        if (body == null) {
            return ResponseEntity.ok("No input, try <a href='/xss/no-cross?body=hello-world'>here</a>");
        }
        body = body.replaceAll("Hello", "---Hello---");
        body += "\n\nSigned by DeepCrossController";
        body = DummyUtil.filterXSS(body);
        ResponseEntity<String> resp = new ResponseEntity(body, HttpStatus.OK);
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

