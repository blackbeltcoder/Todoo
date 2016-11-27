package com.sar.sandpit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ValueConstants;

/**
 * Created by genio on 27/11/16.
 */

@Controller
@RequestMapping("todo")
public class ItemController {

    @RequestMapping( method = RequestMethod.GET)
    public String home(){
        return "todo/index";
    }


    @RequestMapping(value = "items" , method = RequestMethod.GET)
    public String items(ModelMap modalMap){


        modalMap.put("test", "helloworld");

        return "todo/items";
    }
}
