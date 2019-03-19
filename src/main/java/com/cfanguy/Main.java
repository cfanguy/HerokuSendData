package com.cfanguy;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Main {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping(value="/data", method=RequestMethod.GET)
  @ResponseBody
  public String getData() {
    // x, y, event, location
    StringBuilder builder = new StringBuilder();
    try
    {
      builder.append("test");

      // TODO: Figure out way to open file when compiled to .jar
      BufferedReader reader = new BufferedReader(new FileReader("locations.txt"));
      String line;
      while ((line = reader.readLine()) != null)
      {
        builder.append(line);
      }
      reader.close();
      return builder.toString();
    }
    catch (Exception e)
    {
      return builder + e.toString();//"Error occurred reading file";
    }
  }

}
