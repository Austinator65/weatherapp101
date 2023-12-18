package com.example.weatherapp.View;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.weatherapp.Service.WeatherService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Route(value = "weatherService")
@RequestMapping("weatherService")
// @Route("css-view")
@CssImport("./styles/styles.css")
// @Theme("MyTheme")
// @Viewport("width=device-width, minimum-scale=1, initial-scale=1,
// user-scalable=yes, viewport-fit=cover")
public class MainView extends VerticalLayout {

    @Autowired
    private WeatherService weatherService = new WeatherService();
    private Select<String> unitSelect;
    Button showWeatherButton;
    TextField cityTextField;
    H2 currentLocationTitle;
    H1 currentTemp;
    H4 currentDescr;
    String imgUrl;
    Image image;
    String unit;
    String defaultUnit;

    // dashboardDescription() objects
    H3 mainWeatherDescription;
    H3 minTemp;
    H3 maxTemp;
    H3 pressure;
    H3 humidity;
    H3 windSpeed;
    H3 sunRise;
    H3 sunSet;

    HorizontalLayout dashBoardMain;
    HorizontalLayout mainLayout;
    VerticalLayout topLayout;

    public MainView() throws IOException {

        setupLayout();
        setHeader();
        setLogo();
        setUpForm();
        dashboardTitle();

        dashboardDescription();
        dashBoardMain.setVisible(false);

        showWeatherButton.addClickListener(event -> {
            if (!cityTextField.getValue().isEmpty()) {

                try {

                    dashBoardMain.setVisible(true);
                    mainLayout.setVisible(true);
                    unitSelect(unitSelect.getValue());
                    updateUI();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Notification.show("Please enter a city");
            }
        })

        ;
    }

    public void topPane() {

    }

    private void updateUI() throws JSONException {
        String iconCode = null;
        String description = null;
        String main = null;
        String city = cityTextField.getValue();

        // Location update
        currentLocationTitle.setText("Currently in " + cityTextField.getValue());
        JSONObject object = weatherService.getMainObject(city);
        JSONObject sysObject = weatherService.getSun(city);

        // metric

        int temp = object.getInt("temp");
        currentTemp.setText(temp + unit);

        JSONArray weatherArray = weatherService.getWeatherArray(city);

        for (int i = 0; i < weatherArray.length(); i++) {
            JSONObject weatherArrayObject = weatherArray.getJSONObject(i);
            iconCode = weatherArrayObject.getString("icon");
            description = weatherArrayObject.getString("description");
            main = weatherArrayObject.getString("main");
        }

        currentDescr.setText(main);

        // get city icon
        imgUrl = "svg/" + iconCode + ".svg";
        image.setSrc(imgUrl);

        // get description values
        mainWeatherDescription.setText("Cloudiness: " + description);
        int pressureV = object.getInt("pressure");
        pressure.setText("Pressure: " + pressureV + "hpa");
        int humidityV = object.getInt("humidity");
        humidity.setText("Humidity: " + humidityV + "%");
        int tempminV = object.getInt("temp_min");
        minTemp.setText("Min: " + tempminV + unit);
        int maxtempV = object.getInt("temp_max");
        maxTemp.setText("Max: " + maxtempV + unit);

        long sunrise = sysObject.getLong("sunrise") * 1000;
        sunRise.setText("Sunrise: " + convertTime(sunrise));
        long sunset = sysObject.getLong("sunset") * 1000;
        sunSet.setText("Sunset: " + convertTime(sunset));
    }

    public void setupLayout() {

        setClassName("background-layout");
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    public void setHeader() {

        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultVerticalComponentAlignment(Alignment.AUTO);
        H1 title = new H1("Weather");
        title.getStyle().set("font-weight", "bold");
        title.addClassName("header");
        header.add(title);
        add(header);
    }

    private void setLogo() {
        HorizontalLayout logoLayout = new HorizontalLayout();
        Image icon = new Image("icons8-storm-94.png",
                "Alternative text");
        icon.setWidth("150px");
        icon.setHeight("150px");
        icon.setClassName("logo");
        if (icon != null) {
            logoLayout.add(icon);
            add(logoLayout);
        }
    }

    private void setUpForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        cityTextField = new TextField();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);
        showWeatherButton = new Button();
        unitSelect = new Select<>();
        ArrayList<String> items = new ArrayList<>();
        items.add("F");
        items.add("C");

        unitSelect.setItems(items);
        unitSelect.setWidth("13%");
        unitSelect.setValue(items.get(1));

        cityTextField.setWidth("90%");
        cityTextField.setPlaceholder("Enter city name");
        cityTextField.addClassName("bordered-text-field");
        unitSelect.addClassName("bordered-text-field");
        showWeatherButton.addClassName("bordered-text-field");
        showWeatherButton.setIcon(VaadinIcon.SEARCH.create());

        formLayout.add(unitSelect);
        formLayout.add(cityTextField);
        formLayout.add(showWeatherButton);
        add(formLayout);

    }

    private void dashboardTitle() {
        dashBoardMain = new HorizontalLayout();
        dashBoardMain.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        currentLocationTitle = new H2("Currently in Sandton");
        currentLocationTitle.setClassName("textFloat");

        currentTemp = new H1("17C");
        currentTemp.setClassName("textFloat");

        currentDescr = new H4("clouds");
        currentDescr.setClassName("textFloat");

        imgUrl = "svg/02d.svg";
        image = new Image(imgUrl, "");

        image.setWidth("50px");
        image.setHeight("50px");

        dashBoardMain.add(currentLocationTitle, image, currentTemp, currentDescr);
        dashBoardMain.setVisible(false);
        add(dashBoardMain);
    }

    // degree sign u00b0 °
    private void unitSelect(String units) {
        if (units.equals("C")) {
            defaultUnit = "metric";
            unit = "\u00b0" + "C";
            weatherService.setUnit(defaultUnit);
        } else {
            defaultUnit = "imperial";
            unit = "\u00b0" + "F";
            weatherService.setUnit(defaultUnit);
        }

    }

    private void dashboardDescription() {
        mainLayout = new HorizontalLayout();
        VerticalLayout descriptionLayout = new VerticalLayout();
        descriptionLayout.setClassName("descpane");
        // descriptionLayout.getStyle().setBackground("Grey");
        descriptionLayout.setDefaultHorizontalComponentAlignment(Alignment.AUTO);

        mainWeatherDescription = new H3("Weather description");
        mainWeatherDescription.setClassName("h3Text");
        descriptionLayout.add(mainWeatherDescription);

        minTemp = new H3("Min: 56C");
        minTemp.setClassName("h3Text");
        descriptionLayout.add(minTemp);
        maxTemp = new H3("Max: 89F");
        maxTemp.setClassName("h3Text");
        descriptionLayout.add(maxTemp);

        VerticalLayout tempeLayout = new VerticalLayout();
        // tempeLayout.getStyle().setBackground("Grey");
        tempeLayout.setClassName("descpane");
        tempeLayout.setDefaultHorizontalComponentAlignment(Alignment.AUTO);

        pressure = new H3("Pressure: 120 pa");
        pressure.setClassName("h3Text");
        tempeLayout.add(pressure);
        humidity = new H3("Humididty: 30 %");
        humidity.setClassName("h3Text");
        tempeLayout.add(humidity);
        windSpeed = new H3("Wind speed: 120 m/hr");
        windSpeed.setClassName("h3Text");
        tempeLayout.add(windSpeed);
        sunRise = new H3("Sunrise: ");
        sunRise.setClassName("h3Text");
        tempeLayout.add(sunRise);
        sunSet = new H3("Sunset: ");
        sunSet.setClassName("h3Text");
        tempeLayout.add(sunSet);

        dashboardTitle();
        mainLayout.add(descriptionLayout, tempeLayout);
        mainLayout.setVisible(false);
        add(mainLayout);
    }

    private String convertTime(Long time) {
        DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");

        return dateFormat.format(new Date(time));
    }
}