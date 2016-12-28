package com.sar.sandpit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by genio on 20/11/16.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc    full mvc mock
@WebMvcTest   // Just the web context
public class ItemControllerTest {

    @Test
    public void displayItem_StoreHasOneItem_Success() throws Exception {

        throw new NotImplementedException();

        

    }


}
