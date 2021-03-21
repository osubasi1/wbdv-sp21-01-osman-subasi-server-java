package com.example.wbdvsp2103osubasiserverjava.services;

import com.example.wbdvsp2103osubasiserverjava.models.Widget;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WidgetService {
  private List<Widget> widgets = new ArrayList<Widget>();

//  {
//    Widget w1 = new Widget(123l, "HEADING", "Welcome to Widget", 1, "ABC123");
//    Widget w2 = new Widget(234l, "PARAGRAPH", "This is a paragraph", 1, "ABC234");
//    Widget w3 = new Widget(345l, "HEADING", "Welcome to WebDev", 1, "ABC345");
//    Widget w4 = new Widget(567l, "PARAGRAPH", "Lorem Ipsulum", 1, "ABC456");
//    widgets.add(w1);
//    widgets.add(w2);
//    widgets.add(w3);
//    widgets.add(w4);
//  }

  public Widget createWidget(String topicId, Widget widget){
    widget.setTopicId(topicId);
    Long id = (new Date()).getTime();
    widget.setId((id));
    widgets.add(widget);
    return widget;

  }
  public List<Widget> findAllWidgets () {

    return widgets;
  }
  public List<Widget> findWidgetsForTopic(String topicId) {
    List<Widget> ws = new ArrayList<>();
    for(Widget w: widgets){
      if(w.getTopicId().equals(topicId)){
        ws.add(w);
      }
    }
    return ws;
  }
  public Widget findWidgetById(Long id) {
    for(Widget w: widgets) {
      if(w.getId().equals(id)) {
        return w;
      }
    }
    return null;
  }
  public Integer updateWidget(Long id, Widget newWidget){
    for (int i=0; i< widgets.size(); i++){
      Widget w = widgets.get(i);
      if(w.getId().equals(id)){
        widgets.set(i,newWidget);
        return 1;
      }
    }
    return -1;
  }
  public Integer deleteWidget (Long widgetId) {
    int index;
    for (int i=0; i<widgets.size(); i++) {
      if (widgets.get(i).getId() == widgetId) {
        index = i;
        widgets.remove(index);
        return 1;
      }
    }
    return -1;
  }
}
