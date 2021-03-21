package com.example.wbdvsp2103osubasiserverjava.controllers;
import com.example.wbdvsp2103osubasiserverjava.models.Widget;
import com.example.wbdvsp2103osubasiserverjava.services.WidgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = {"http://localhost:3000", "http://my-heroku-app.com"})
@RestController
@CrossOrigin(origins = "*")
public class WidgetController {
  @Autowired
  WidgetService service;// = new WidgetService();

  @PostMapping("/api/topics/{tid}/widgets")
  public Widget createWidget(
          @PathVariable("tid") String topicId,
          @RequestBody Widget widget) {
    System.out.println("hi from controller create widget");
    System.out.println("widget.id is:" + widget.getId());
    widget.setTopicId(topicId);
    return service.createWidget(topicId, widget);
  }

  @GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findWidgetsForTopic(
          @PathVariable("tid") String topicId
  ){
    return service.findWidgetsForTopic(topicId);
  }

  @PutMapping("/api/widgets/{wid}")
  public Integer updateWidget(
          @PathVariable("wid") long id,
          @RequestBody Widget widget) {
    return service.updateWidget(id,widget);
  }

  @DeleteMapping("/api/widgets/{wid}")
  public Integer deleteWidget(
          @PathVariable("wid") long id) {
    return service.deleteWidget(id);
  }

  @GetMapping("/api/widgets")
  public List<Widget> findAllWidgets(){
    return service.findAllWidgets();
  }

  @GetMapping("/api/widgets/{wid}")
  public Widget findWidgetById(
          @PathVariable("wid") long id){
    return service.findWidgetById(id);
  }
}
