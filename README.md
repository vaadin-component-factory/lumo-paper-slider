# Lumo Paper Slider project

Version of paper-slider based field component for Vaadin 14 with Lumo styles and full field API.

## Development instructions

### Important Files 
* PaperSlider.java: this is the addon-on component class.
* SliderView.java: A View class that let's you test the component you are building. 
* PaperSliderIT.java: Small set of integration tests, TestBench requried. 

### Deployment

Starting the test/demo server:
```
mvn jetty:run
```

This deploys demo at http://localhost:8080
 
### Integration test

To run Integration Tests, execute `mvn verify -Pit,production`.

## Publishing to Vaadin Directory

You should change the `organisation.name` property in `pom.xml` to your own name/organization.

```
    <organization>
        <name>###author###</name>
    </organization>
```

You can create the zip package needed for [Vaadin Directory](https://vaadin.com/directory/) using

```
mvn versions:set -DnewVersion=1.0.0 # You cannot publish snapshot versions 
mvn install -Pdirectory
```

The package is created as `target/lumo-paper-slider-1.0.0.zip`

For more information or to upload the package, visit https://vaadin.com/directory/my-components?uploadNewComponent
