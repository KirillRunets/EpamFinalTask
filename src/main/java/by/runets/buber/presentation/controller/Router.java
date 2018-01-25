package by.runets.buber.presentation.controller;

import by.runets.buber.infrastructure.constant.JspPagePath;

public class Router {
    public enum RouteType {
        FORWARD, REDIRECT;
    }

    private String pagePath;
    private RouteType routeType = RouteType.FORWARD;

    public String getPagePath(){
        return  pagePath;
    }

    public void setPagePath(String pagePath) {
        if (pagePath == null){
            this.pagePath = JspPagePath.MAIN_PAGE;
        }
        this.pagePath = pagePath;
    }

    public void setRouteType(RouteType routeType) {
        if (routeType == null){
            this.routeType = RouteType.FORWARD;
        }
        this.routeType = routeType;
    }

    public RouteType getRouteType() {
        return routeType;
    }
}
