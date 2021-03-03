package com.deferredres.DeferredResult;

import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class DeferredResultController {
	
	private final static Logger LOG = LoggerFactory.getLogger(DeferredResultController.class);

	
	@GetMapping("/async-deferredresult")
public DeferredResult<ResponseEntity<?>> handleReqDefResult(Model model) {
    LOG.info("Received async-deferredresult request");
    DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();
    
    ForkJoinPool.commonPool().submit(() -> {
        LOG.info("Processing in separate thread");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
        }
        output.setResult(ResponseEntity.ok("ok"));
    });
    
    LOG.info("servlet thread freed");
    return output;
}
	
	

}
