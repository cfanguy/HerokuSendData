package com.cfanguy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Main {

  @Autowired
  private ResourceLoader resourceLoader;

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
    // x, y, event, location within file
    final Resource fileResource = resourceLoader.getResource("classpath:/public/locations.json");

    StringBuilder builder = new StringBuilder();
    try
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(fileResource.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null)
      {
        if(!line.contains("//")) {
          builder.append(line);
        };        
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
