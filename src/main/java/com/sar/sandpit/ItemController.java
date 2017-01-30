package com.sar.sandpit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    ItemServiceable itemService;

    //public ItemController(){}
    //@Autowired
    public ItemController(ItemServiceable itemService) {
        this.itemService=itemService;
    }


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


    //TODO TEST UNDERSTANING
    @GetMapping(path = "addItemTest")
    public String addItemResultTest(@ModelAttribute ItemDto itemDto, Model model){
        //itemService.add(new Item(item.getId(), item.getDetails()));

        model.addAttribute("item",
                           // new Item(item.getId(), item.getDetails())
                           itemService.add(new Item(itemDto.getId(), itemDto.getDetails()))
        );
        return
                "todo/addItemResultTest";


    }



    @PostMapping(path = "addItem")
    public String addItemResult(@ModelAttribute ItemDto itemDto, Model model){
        model.addAttribute("item",
                          // new Item(item.getId(), item.getDetails())
                           itemService.add(new Item(itemDto.getId(), itemDto.getDetails()))
        );
        return
                "todo/addItemResult";

//                "todo/items";
    }
    @GetMapping(path="addItem")
    public String addItem(Model model){

        model.addAttribute("item",new ItemDto());
        return "todo/addItem";
    }


//    @RequestMapping("addItemResult")
//    public String addItemResult(Model model){
//
//        return a
//    }

    private void test() {
//        Long i =1L;
    }



    @RequestMapping(value = "/items" , method = RequestMethod.GET)
    public String items(ModelMap modalMap){
       // modalMap.put("test", "helloworld");
       // throw new NotImplementedException();

        modalMap.addAttribute("items",
                itemService.getItems());        return null;
        //return "todo/items";
    }

    @GetMapping(value = "/deleteItem/{id}")
    public String deleteItem( @PathVariable  Long id, ModelMap model ){
        itemService.delete(id);

        //return null;
        //return  "redirect:todo/items";
        return "redirect:/todo/items";
    }
}
