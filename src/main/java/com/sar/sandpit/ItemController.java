package com.sar.sandpit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ValueConstants;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by genio on 27/11/16.
 */

@Controller
@RequestMapping("/todo")
public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping( path ="index", method = RequestMethod.GET)
    public String home(){

       // throw new NotImplementedException() ;
        return "todo/index";
    }

    @RequestMapping(path ="item")
    public String item(ModelMap modal){
        modal.put("item", itemService.getItem(1L));
        return "todo/item";
        //throw  new NotImplementedException();
    }


    @RequestMapping(value = "/items" , method = RequestMethod.GET)
    public String items(ModelMap modalMap){
       // modalMap.put("test", "helloworld");
        throw new NotImplementedException();
        //return "todo/items";
    }
}
