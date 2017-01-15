package com.sar.sandpit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(path ="item/{id}")
    public String item(@PathVariable Long id, ModelMap modal){
        modal.put("item", itemService.getItem(id));
        return "todo/item";
        //throw  new NotImplementedException();
    }

    @PostMapping(path = "addItem")
    public String addItem(@ModelAttribute ItemDto item, Model model){

      //  ItemDto i = item;
     //   throw new NotImplementedException();

       // model.addAttribute("item",item );
        return "todo/addItemResult";

    }


//    @RequestMapping("addItemResult")
//    public String addItemResult(Model model){
//
//        return a
//    }






    @RequestMapping(value = "/items" , method = RequestMethod.GET)
    public String items(ModelMap modalMap){
       // modalMap.put("test", "helloworld");
        throw new NotImplementedException();
        //return "todo/items";
    }
}
