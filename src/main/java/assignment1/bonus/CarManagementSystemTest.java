package assignment1.bonus;

import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CarManagementSystemTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private CustomCarBuilder builder;
    private CarManagementSystem system;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        builder = new CustomCarBuilder();
        system = new CarManagementSystem();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    private String getOutput() {
        return outputStream.toString();
    }

    private void clearOutput() {
        outputStream.reset();
    }

    @Test
    @DisplayName("EngineType: V4 should have correct properties")
    public void engineType_V4_ShouldHaveCorrectProperties() {
        EngineType engine = EngineType.V4;
        assertEquals("V4", engine.getName());
        assertEquals(150, engine.getHorsepower());
        assertEquals(4, engine.getCylinders());
        assertNotNull(engine.toString());
    }

    @Test
    @DisplayName("EngineType: V6 should have correct properties")
    public void engineType_V6_ShouldHaveCorrectProperties() {
        EngineType engine = EngineType.V6;
        assertEquals("V6", engine.getName());
        assertEquals(280, engine.getHorsepower());
        assertEquals(6, engine.getCylinders());
    }

    @Test
    @DisplayName("EngineType: V8 should have correct properties")
    public void engineType_V8_ShouldHaveCorrectProperties() {
        EngineType engine = EngineType.V8;
        assertEquals("V8", engine.getName());
        assertEquals(400, engine.getHorsepower());
        assertEquals(8, engine.getCylinders());
    }

    @Test
    @DisplayName("EngineType: Electric should have zero cylinders")
    public void engineType_Electric_ShouldHaveZeroCylinders() {
        EngineType engine = EngineType.ELECTRIC;
        assertEquals("Electric", engine.getName());
        assertEquals(350, engine.getHorsepower());
        assertEquals(0, engine.getCylinders());
    }

    @Test
    @DisplayName("EngineType: Hybrid should have correct properties")
    public void engineType_Hybrid_ShouldHaveCorrectProperties() {
        EngineType engine = EngineType.HYBRID;
        assertEquals("Hybrid", engine.getName());
        assertEquals(220, engine.getHorsepower());
        assertEquals(4, engine.getCylinders());
    }

    @ParameterizedTest
    @EnumSource(EngineType.class)
    @DisplayName("EngineType: All types should have valid toString")
    public void engineType_AllTypes_ShouldHaveValidToString(EngineType engine) {
        String str = engine.toString();
        assertNotNull(str);
        assertFalse(str.isEmpty());
        assertTrue(str.contains(engine.getName()));
    }

    @Test
    @DisplayName("TransmissionType: Manual 5-speed properties")
    public void transmissionType_Manual5Speed_ShouldHaveCorrectProperties() {
        TransmissionType trans = TransmissionType.MANUAL_5_SPEED;
        assertEquals("5-Speed Manual", trans.getName());
        assertEquals(5, trans.getGears());
        assertFalse(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: Manual 6-speed properties")
    public void transmissionType_Manual6Speed_ShouldHaveCorrectProperties() {
        TransmissionType trans = TransmissionType.MANUAL_6_SPEED;
        assertEquals(6, trans.getGears());
        assertFalse(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: Automatic 8-speed properties")
    public void transmissionType_Automatic8Speed_ShouldHaveCorrectProperties() {
        TransmissionType trans = TransmissionType.AUTOMATIC_8_SPEED;
        assertEquals(8, trans.getGears());
        assertTrue(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: CVT properties")
    public void transmissionType_CVT_ShouldHaveCorrectProperties() {
        TransmissionType trans = TransmissionType.CVT;
        assertEquals(0, trans.getGears());
        assertTrue(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: Dual-clutch properties")
    public void transmissionType_DualClutch_ShouldHaveCorrectProperties() {
        TransmissionType trans = TransmissionType.DUAL_CLUTCH;
        assertEquals(7, trans.getGears());
        assertTrue(trans.isAutomatic());
    }

    @ParameterizedTest
    @EnumSource(TransmissionType.class)
    @DisplayName("TransmissionType: All types should have valid toString")
    public void transmissionType_AllTypes_ShouldHaveValidToString(TransmissionType trans) {
        assertNotNull(trans.toString());
        assertFalse(trans.toString().isEmpty());
    }

    @ParameterizedTest
    @EnumSource(ExteriorColor.class)
    @DisplayName("ExteriorColor: All colors should have valid toString")
    public void exteriorColor_AllColors_ShouldHaveValidToString(ExteriorColor color) {
        assertNotNull(color.toString());
        assertFalse(color.toString().isEmpty());
    }

    @Test
    @DisplayName("ExteriorColor: Should have expected number of options")
    public void exteriorColor_ShouldHaveExpectedOptions() {
        assertEquals(7, ExteriorColor.values().length);
    }

    @Test
    @DisplayName("RimType: Standard 16 should have size 16")
    public void rimType_Standard16_ShouldHaveSize16() {
        assertEquals(16, RimType.STANDARD_16.getSize());
    }

    @Test
    @DisplayName("RimType: Performance 21 should have size 21")
    public void rimType_Performance21_ShouldHaveSize21() {
        assertEquals(21, RimType.PERFORMANCE_21.getSize());
    }

    @ParameterizedTest
    @EnumSource(RimType.class)
    @DisplayName("RimType: All types should have valid properties")
    public void rimType_AllTypes_ShouldHaveValidProperties(RimType rim) {
        assertTrue(rim.getSize() >= 16);
        assertTrue(rim.getSize() <= 21);
        assertNotNull(rim.toString());
    }

    @Test
    @DisplayName("InteriorFeature: Leather seats should have correct price")
    public void interiorFeature_LeatherSeats_ShouldHaveCorrectPrice() {
        InteriorFeature feature = InteriorFeature.LEATHER_SEATS;
        assertEquals("Leather Seats", feature.getName());
        assertEquals(1500.0, feature.getPrice(), 0.01);
    }

    @Test
    @DisplayName("InteriorFeature: GPS should have correct price")
    public void interiorFeature_GPS_ShouldHaveCorrectPrice() {
        InteriorFeature feature = InteriorFeature.GPS_NAVIGATION;
        assertEquals(1200.0, feature.getPrice(), 0.01);
    }

    @ParameterizedTest
    @EnumSource(InteriorFeature.class)
    @DisplayName("InteriorFeature: All features should have non-negative price")
    public void interiorFeature_AllFeatures_ShouldHaveNonNegativePrice(InteriorFeature feature) {
        assertTrue(feature.getPrice() >= 0);
        assertNotNull(feature.getName());
        assertNotNull(feature.toString());
    }

    @Test
    @DisplayName("ExteriorOption: Sunroof should have correct price")
    public void exteriorOption_Sunroof_ShouldHaveCorrectPrice() {
        ExteriorOption option = ExteriorOption.SUNROOF;
        assertEquals("Panoramic Sunroof", option.getName());
        assertEquals(1200.0, option.getPrice(), 0.01);
    }

    @ParameterizedTest
    @EnumSource(ExteriorOption.class)
    @DisplayName("ExteriorOption: All options should have non-negative price")
    public void exteriorOption_AllOptions_ShouldHaveNonNegativePrice(ExteriorOption option) {
        assertTrue(option.getPrice() >= 0);
        assertNotNull(option.getName());
        assertNotNull(option.toString());
    }

    @Test
    @DisplayName("SafetyFeature: ABS should be standard")
    public void safetyFeature_ABS_ShouldBeStandard() {
        SafetyFeature feature = SafetyFeature.ABS;
        assertTrue(feature.isStandard());
        assertEquals(0.0, feature.getPrice(), 0.01);
    }

    @Test
    @DisplayName("SafetyFeature: Front airbags should be standard")
    public void safetyFeature_FrontAirbags_ShouldBeStandard() {
        SafetyFeature feature = SafetyFeature.AIRBAGS_FRONT;
        assertTrue(feature.isStandard());
        assertEquals(0.0, feature.getPrice(), 0.01);
    }

    @Test
    @DisplayName("SafetyFeature: Rear camera should not be standard")
    public void safetyFeature_RearCamera_ShouldNotBeStandard() {
        SafetyFeature feature = SafetyFeature.REAR_CAMERA;
        assertFalse(feature.isStandard());
        assertTrue(feature.getPrice() > 0);
    }

    @ParameterizedTest
    @EnumSource(SafetyFeature.class)
    @DisplayName("SafetyFeature: All features should have valid properties")
    public void safetyFeature_AllFeatures_ShouldHaveValidProperties(SafetyFeature feature) {
        assertTrue(feature.getPrice() >= 0);
        assertNotNull(feature.getName());
        assertNotNull(feature.toString());
        if (feature.isStandard()) {
            assertEquals(0.0, feature.getPrice(), 0.01);
        }
    }

    @Test
    @DisplayName("Builder: Should create builder with default values")
    public void builder_Constructor_ShouldSetDefaults() {
        assertNotNull(builder);
    }

    @Test
    @DisplayName("Builder: Should set model correctly")
    public void builder_SetModel_ShouldWork() {
        Car car = builder.setModel("Test Model").build();
        assertEquals("Test Model", car.getModel());
    }

    @Test
    @DisplayName("Builder: Should set year correctly")
    public void builder_SetYear_ShouldWork() {
        int currentYear = Year.now().getValue();
        Car car = builder.setYear(currentYear).build();
        assertEquals(currentYear, car.getYear());
    }

    @Test
    @DisplayName("Builder: Should set base price correctly")
    public void builder_SetBasePrice_ShouldWork() {
        Car car = builder.setBasePrice(50000.0).build();
        assertEquals(50000.0, car.getBasePrice(), 0.01);
    }

    @Test
    @DisplayName("Builder: Should set engine correctly")
    public void builder_SetEngine_ShouldWork() {
        Car car = builder.setEngine(EngineType.V8).build();
        assertEquals(EngineType.V8, car.getEngine());
    }

    @Test
    @DisplayName("Builder: Should set transmission correctly")
    public void builder_SetTransmission_ShouldWork() {
        Car car = builder.setTransmission(TransmissionType.AUTOMATIC_8_SPEED).build();
        assertEquals(TransmissionType.AUTOMATIC_8_SPEED, car.getTransmission());
    }

    @Test
    @DisplayName("Builder: Should set color correctly")
    public void builder_SetColor_ShouldWork() {
        Car car = builder.setColor(ExteriorColor.RED).build();
        assertEquals(ExteriorColor.RED, car.getColor());
    }

    @Test
    @DisplayName("Builder: Should set rims correctly")
    public void builder_SetRims_ShouldWork() {
        Car car = builder.setRims(RimType.SPORT_19).build();
        assertEquals(RimType.SPORT_19, car.getRims());
    }

    @Test
    @DisplayName("Builder: Should add single interior feature")
    public void builder_AddInteriorFeature_ShouldWork() {
        Car car = builder.addInteriorFeature(InteriorFeature.LEATHER_SEATS).build();
        assertTrue(car.getInteriorFeatures().contains(InteriorFeature.LEATHER_SEATS));
    }

    @Test
    @DisplayName("Builder: Should add multiple interior features")
    public void builder_AddInteriorFeatures_ShouldWork() {
        Car car = builder.addInteriorFeatures(
                InteriorFeature.LEATHER_SEATS,
                InteriorFeature.GPS_NAVIGATION,
                InteriorFeature.HEATED_SEATS
        ).build();

        Set<InteriorFeature> features = car.getInteriorFeatures();
        assertEquals(3, features.size());
        assertTrue(features.contains(InteriorFeature.LEATHER_SEATS));
        assertTrue(features.contains(InteriorFeature.GPS_NAVIGATION));
        assertTrue(features.contains(InteriorFeature.HEATED_SEATS));
    }

    @Test
    @DisplayName("Builder: Should add single exterior option")
    public void builder_AddExteriorOption_ShouldWork() {
        Car car = builder.addExteriorOption(ExteriorOption.SUNROOF).build();
        assertTrue(car.getExteriorOptions().contains(ExteriorOption.SUNROOF));
    }

    @Test
    @DisplayName("Builder: Should add multiple exterior options")
    public void builder_AddExteriorOptions_ShouldWork() {
        Car car = builder.addExteriorOptions(
                ExteriorOption.SUNROOF,
                ExteriorOption.SPOILER
        ).build();

        assertEquals(2, car.getExteriorOptions().size());
    }

    @Test
    @DisplayName("Builder: Should add single safety feature")
    public void builder_AddSafetyFeature_ShouldWork() {
        Car car = builder.addSafetyFeature(SafetyFeature.REAR_CAMERA).build();
        assertTrue(car.getSafetyFeatures().contains(SafetyFeature.REAR_CAMERA));
    }

    @Test
    @DisplayName("Builder: Should add multiple safety features")
    public void builder_AddSafetyFeatures_ShouldWork() {
        Car car = builder.addSafetyFeatures(
                SafetyFeature.REAR_CAMERA,
                SafetyFeature.BLIND_SPOT_MONITOR
        ).build();

        assertTrue(car.getSafetyFeatures().contains(SafetyFeature.REAR_CAMERA));
        assertTrue(car.getSafetyFeatures().contains(SafetyFeature.BLIND_SPOT_MONITOR));
    }

    @Test
    @DisplayName("Builder: Should include standard safety features by default")
    public void builder_Build_ShouldIncludeStandardSafetyFeatures() {
        Car car = builder.build();
        assertTrue(car.getSafetyFeatures().contains(SafetyFeature.ABS));
        assertTrue(car.getSafetyFeatures().contains(SafetyFeature.AIRBAGS_FRONT));
    }

    @Test
    @DisplayName("Builder: Fluent interface should work")
    public void builder_FluentInterface_ShouldWork() {
        Car car = builder
                .setModel("Fluent Test")
                .setEngine(EngineType.V6)
                .setTransmission(TransmissionType.AUTOMATIC_6_SPEED)
                .setColor(ExteriorColor.BLUE)
                .setRims(RimType.ALLOY_18)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addExteriorOption(ExteriorOption.SUNROOF)
                .build();

        assertEquals("Fluent Test", car.getModel());
        assertEquals(EngineType.V6, car.getEngine());
    }

    @Test
    @DisplayName("Builder: Reset should restore defaults")
    public void builder_Reset_ShouldRestoreDefaults() {
        builder.setModel("Custom").setEngine(EngineType.V8);
        builder.reset();
        Car car = builder.build();

        assertEquals(EngineType.V4, car.getEngine());
    }

    @Test
    @DisplayName("Builder: Should be reusable after build")
    public void builder_ShouldBeReusableAfterBuild() {
        Car car1 = builder.setModel("Car 1").build();
        builder.reset();
        Car car2 = builder.setModel("Car 2").build();

        assertNotEquals(car1.getVin(), car2.getVin());
        assertEquals("Car 1", car1.getModel());
        assertEquals("Car 2", car2.getModel());
    }

    @Test
    @DisplayName("Builder: Should throw exception for null model")
    public void builder_SetModel_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setModel(null));
    }

    @Test
    @DisplayName("Builder: Should throw exception for empty model")
    public void builder_SetModel_Empty_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setModel(""));
    }

    @Test
    @DisplayName("Builder: Should throw exception for whitespace model")
    public void builder_SetModel_Whitespace_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setModel("   "));
    }

    @Test
    @DisplayName("Builder: Should throw exception for past year")
    public void builder_SetYear_Past_ShouldThrowException() {
        int pastYear = Year.now().getValue() - 1;
        assertThrows(IllegalArgumentException.class, () -> builder.setYear(pastYear));
    }

    @Test
    @DisplayName("Builder: Should throw exception for far future year")
    public void builder_SetYear_FarFuture_ShouldThrowException() {
        int farFutureYear = Year.now().getValue() + 5;
        assertThrows(IllegalArgumentException.class, () -> builder.setYear(farFutureYear));
    }

    @Test
    @DisplayName("Builder: Should throw exception for negative price")
    public void builder_SetBasePrice_Negative_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setBasePrice(-1000.0));
    }

    @Test
    @DisplayName("Builder: Should throw exception for null engine")
    public void builder_SetEngine_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setEngine(null));
    }

    @Test
    @DisplayName("Builder: Should throw exception for null transmission")
    public void builder_SetTransmission_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setTransmission(null));
    }

    @Test
    @DisplayName("Builder: Should throw exception for null color")
    public void builder_SetColor_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setColor(null));
    }

    @Test
    @DisplayName("Builder: Should throw exception for null rims")
    public void builder_SetRims_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setRims(null));
    }

    @Test
    @DisplayName("Builder: Should throw exception for null interior feature")
    public void builder_AddInteriorFeature_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.addInteriorFeature(null));
    }

    @Test
    @DisplayName("Builder: Should throw exception for null exterior option")
    public void builder_AddExteriorOption_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.addExteriorOption(null));
    }

    @Test
    @DisplayName("Builder: Should throw exception for null safety feature")
    public void builder_AddSafetyFeature_Null_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.addSafetyFeature(null));
    }

    @Test
    @DisplayName("Builder: Electric with manual should throw exception")
    public void builder_ElectricWithManual_ShouldThrowException() {
        builder.setEngine(EngineType.ELECTRIC)
                .setTransmission(TransmissionType.MANUAL_5_SPEED);
        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @Test
    @DisplayName("Builder: Electric with automatic should work")
    public void builder_ElectricWithAutomatic_ShouldWork() {
        Car car = builder
                .setEngine(EngineType.ELECTRIC)
                .setTransmission(TransmissionType.CVT)
                .build();
        assertEquals(EngineType.ELECTRIC, car.getEngine());
    }

    @Test
    @DisplayName("Builder: getCurrentConfigurationSummary should return info")
    public void builder_GetCurrentConfigurationSummary_ShouldWork() {
        builder.setModel("Summary Test");
        String summary = builder.getCurrentConfigurationSummary();
        assertNotNull(summary);
        assertFalse(summary.isEmpty());
    }

    @Test
    @DisplayName("Car: Should generate unique VIN")
    public void car_ShouldGenerateUniqueVIN() {
        Car car1 = builder.setModel("Car 1").build();
        builder.reset();
        Car car2 = builder.setModel("Car 2").build();

        assertNotNull(car1.getVin());
        assertNotNull(car2.getVin());
        assertNotEquals(car1.getVin(), car2.getVin());
        assertTrue(car1.getVin().startsWith("VIN-"));
    }

    @Test
    @DisplayName("Car: Should calculate total price correctly")
    public void car_GetTotalPrice_ShouldCalculateCorrectly() {
        Car car = builder
                .setBasePrice(30000.0)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addInteriorFeature(InteriorFeature.GPS_NAVIGATION)
                .addExteriorOption(ExteriorOption.SUNROOF)
                .addSafetyFeature(SafetyFeature.REAR_CAMERA)
                .build();

        double expectedTotal = 30000.0 + 1500.0 + 1200.0 + 1200.0 + 500.0;
        assertEquals(expectedTotal, car.getTotalPrice(), 0.01);
    }

    @Test
    @DisplayName("Car: Should calculate options price correctly")
    public void car_GetOptionsPrice_ShouldCalculateCorrectly() {
        Car car = builder
                .setBasePrice(30000.0)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addExteriorOption(ExteriorOption.SUNROOF)
                .build();

        double expectedOptions = 1500.0 + 1200.0;
        assertEquals(expectedOptions, car.getOptionsPrice(), 0.01);
    }

    @Test
    @DisplayName("Car: toString should include year and model")
    public void car_ToString_ShouldWork() {
        Car car = builder.setModel("Test Model").build();
        String str = car.toString();
        assertTrue(str.contains("Test Model"));
    }

    @Test
    @DisplayName("Car: Interior features should be immutable")
    public void car_InteriorFeatures_ShouldBeImmutable() {
        Car car = builder.addInteriorFeature(InteriorFeature.LEATHER_SEATS).build();
        Set<InteriorFeature> features = car.getInteriorFeatures();
        assertThrows(UnsupportedOperationException.class,
                () -> features.add(InteriorFeature.GPS_NAVIGATION));
    }

    @Test
    @DisplayName("Car: Exterior options should be immutable")
    public void car_ExteriorOptions_ShouldBeImmutable() {
        Car car = builder.addExteriorOption(ExteriorOption.SUNROOF).build();
        Set<ExteriorOption> options = car.getExteriorOptions();
        assertThrows(UnsupportedOperationException.class,
                () -> options.add(ExteriorOption.SPOILER));
    }

    @Test
    @DisplayName("Car: Safety features should be immutable")
    public void car_SafetyFeatures_ShouldBeImmutable() {
        Car car = builder.build();
        Set<SafetyFeature> features = car.getSafetyFeatures();
        assertThrows(UnsupportedOperationException.class,
                () -> features.add(SafetyFeature.NIGHT_VISION));
    }

    @Test
    @DisplayName("Configurator: Should build economy car")
    public void configurator_BuildEconomyCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildEconomyCar("Economy Model");

        assertNotNull(car);
        assertEquals("Economy Model", car.getModel());
        assertEquals(EngineType.V4, car.getEngine());
    }

    @Test
    @DisplayName("Configurator: Should build family car")
    public void configurator_BuildFamilyCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildFamilyCar("Family Model");

        assertNotNull(car);
        assertEquals("Family Model", car.getModel());
        assertEquals(EngineType.V6, car.getEngine());
        assertTrue(car.getTransmission().isAutomatic());
        assertFalse(car.getInteriorFeatures().isEmpty());
    }

    @Test
    @DisplayName("Configurator: Should build sports car")
    public void configurator_BuildSportsCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildSportsCar("Sports Model");

        assertNotNull(car);
        assertEquals(EngineType.V8, car.getEngine());
    }

    @Test
    @DisplayName("Configurator: Should build luxury car")
    public void configurator_BuildLuxuryCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildLuxuryCar("Luxury Model");

        assertNotNull(car);
        assertEquals(EngineType.V8, car.getEngine());
        assertTrue(car.getInteriorFeatures().size() >= 5);
    }

    @Test
    @DisplayName("Configurator: Should build electric car")
    public void configurator_BuildElectricCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildElectricCar("Electric Model");

        assertNotNull(car);
        assertEquals(EngineType.ELECTRIC, car.getEngine());
        assertTrue(car.getTransmission().isAutomatic());
    }

    @Test
    @DisplayName("Configurator: Economy car should be cheapest")
    public void configurator_EconomyCar_ShouldBeCheapest() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());

        Car economy = configurator.buildEconomyCar("Economy");
        Car family = configurator.buildFamilyCar("Family");
        Car luxury = configurator.buildLuxuryCar("Luxury");

        assertTrue(economy.getTotalPrice() < family.getTotalPrice());
        assertTrue(family.getTotalPrice() < luxury.getTotalPrice());
    }

    @Test
    @DisplayName("Configurator: setBuilder should work")
    public void configurator_SetBuilder_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        configurator.setBuilder(new CustomCarBuilder());
        Car car = configurator.buildEconomyCar("Test");
        assertNotNull(car);
    }

    @Test
    @DisplayName("Factory: Should create PDF document")
    public void factory_CreateDocument_PDF_ShouldWork() {
        Document doc = DocumentFactory.createDocument("pdf");
        assertNotNull(doc);
        assertInstanceOf(PDFDocument.class, doc);
        assertEquals("PDF", doc.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create Word document")
    public void factory_CreateDocument_Word_ShouldWork() {
        Document doc = DocumentFactory.createDocument("word");
        assertNotNull(doc);
        assertInstanceOf(WordDocument.class, doc);
        assertEquals("Word", doc.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create HTML document")
    public void factory_CreateDocument_HTML_ShouldWork() {
        Document doc = DocumentFactory.createDocument("html");
        assertNotNull(doc);
        assertInstanceOf(HTMLDocument.class, doc);
        assertEquals("HTML", doc.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create Markdown document")
    public void factory_CreateDocument_Markdown_ShouldWork() {
        Document doc = DocumentFactory.createDocument("markdown");
        assertNotNull(doc);
        assertInstanceOf(MarkdownDocument.class, doc);
        assertEquals("Markdown", doc.getFormatName());
    }

    @Test
    @DisplayName("Factory: Should create document with alias")
    public void factory_CreateDocument_Alias_ShouldWork() {
        Document docx = DocumentFactory.createDocument("docx");
        Document htm = DocumentFactory.createDocument("htm");
        Document md = DocumentFactory.createDocument("md");

        assertInstanceOf(WordDocument.class, docx);
        assertInstanceOf(HTMLDocument.class, htm);
        assertInstanceOf(MarkdownDocument.class, md);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PDF", "Pdf", "pdf", "WORD", "Word", "HTML", "html"})
    @DisplayName("Factory: Should be case insensitive")
    public void factory_CreateDocument_CaseInsensitive(String type) {
        Document doc = DocumentFactory.createDocument(type);
        assertNotNull(doc);
    }

    @Test
    @DisplayName("Factory: Should throw exception for unsupported type")
    public void factory_CreateDocument_Unsupported_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> DocumentFactory.createDocument("xyz"));
    }

    @Test
    @DisplayName("Factory: isTypeSupported should work")
    public void factory_IsTypeSupported_ShouldWork() {
        assertTrue(DocumentFactory.isTypeSupported("pdf"));
        assertTrue(DocumentFactory.isTypeSupported("word"));
        assertTrue(DocumentFactory.isTypeSupported("html"));
        assertFalse(DocumentFactory.isTypeSupported("unknown"));
    }

    @Test
    @DisplayName("Factory: getSupportedTypes should return types")
    public void factory_GetSupportedTypes_ShouldWork() {
        String types = DocumentFactory.getSupportedTypes();
        assertNotNull(types);
        assertFalse(types.isEmpty());
        assertTrue(types.contains("pdf"));
    }

    @Test
    @DisplayName("Factory: registerDocument should work")
    public void factory_RegisterDocument_ShouldWork() {
        String customType = "custom" + System.currentTimeMillis();
        DocumentFactory.registerDocument(customType, PDFDocument::new);
        assertTrue(DocumentFactory.isTypeSupported(customType));
    }

    @Test
    @DisplayName("PDFDocument: open should work")
    public void pdfDocument_Open_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.open();
        assertFalse(getOutput().isEmpty());
    }

    @Test
    @DisplayName("PDFDocument: save should store content")
    public void pdfDocument_Save_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.save("Test content");
        assertEquals("Test content", pdf.getContent());
    }

    @Test
    @DisplayName("PDFDocument: display should work")
    public void pdfDocument_Display_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.save("Test");
        clearOutput();
        pdf.display();
        assertFalse(getOutput().isEmpty());
    }

    @Test
    @DisplayName("PDFDocument: exportContent should work")
    public void pdfDocument_ExportContent_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.save("Test content");
        String exported = pdf.exportContent();
        assertNotNull(exported);
        assertTrue(exported.contains("PDF"));
        assertTrue(exported.contains("Test content"));
    }

    @Test
    @DisplayName("PDFDocument: setCar should work")
    public void pdfDocument_SetCar_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        Car car = builder.setModel("PDF Test Car").build();
        pdf.setCar(car);

        assertEquals(car, pdf.getCar());
        assertNotNull(pdf.getTitle());
        assertTrue(pdf.getTitle().contains("PDF Test Car"));
    }

    @Test
    @DisplayName("WordDocument: open should work")
    public void wordDocument_Open_ShouldWork() {
        WordDocument word = new WordDocument();
        word.open();
        assertFalse(getOutput().isEmpty());
    }

    @Test
    @DisplayName("WordDocument: save should store content")
    public void wordDocument_Save_ShouldWork() {
        WordDocument word = new WordDocument();
        word.save("Word content");
        assertEquals("Word content", word.getContent());
    }

    @Test
    @DisplayName("WordDocument: display should work")
    public void wordDocument_Display_ShouldWork() {
        WordDocument word = new WordDocument();
        word.save("Test");
        clearOutput();
        word.display();
        assertFalse(getOutput().isEmpty());
    }

    @Test
    @DisplayName("WordDocument: exportContent should return XML")
    public void wordDocument_ExportContent_ShouldWork() {
        WordDocument word = new WordDocument();
        word.save("Test content");
        String exported = word.exportContent();
        assertTrue(exported.contains("<?xml"));
        assertTrue(exported.contains("w:document"));
    }

    @Test
    @DisplayName("HTMLDocument: open should work")
    public void htmlDocument_Open_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.open();
        assertFalse(getOutput().isEmpty());
    }

    @Test
    @DisplayName("HTMLDocument: save should store content")
    public void htmlDocument_Save_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.save("HTML content");
        assertEquals("HTML content", html.getContent());
    }

    @Test
    @DisplayName("HTMLDocument: display should work")
    public void htmlDocument_Display_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.save("Test");
        clearOutput();
        html.display();
        assertFalse(getOutput().isEmpty());
    }

    @Test
    @DisplayName("HTMLDocument: exportContent should return valid HTML")
    public void htmlDocument_ExportContent_ShouldWork() {
        HTMLDocument html = new HTMLDocument();
        html.save("Test content");
        String exported = html.exportContent();
        assertTrue(exported.contains("<!DOCTYPE html>"));
        assertTrue(exported.contains("<html"));
        assertTrue(exported.contains("</html>"));
    }

    @Test
    @DisplayName("MarkdownDocument: open should work")
    public void markdownDocument_Open_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.open();
        assertFalse(getOutput().isEmpty());
    }

    @Test
    @DisplayName("MarkdownDocument: save should store content")
    public void markdownDocument_Save_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.save("# Markdown content");
        assertEquals("# Markdown content", md.getContent());
    }

    @Test
    @DisplayName("MarkdownDocument: display should work")
    public void markdownDocument_Display_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.save("Test");
        clearOutput();
        md.display();
        String output = getOutput();
        assertTrue(output.contains("```markdown"));
    }

    @Test
    @DisplayName("MarkdownDocument: exportContent should work")
    public void markdownDocument_ExportContent_ShouldWork() {
        MarkdownDocument md = new MarkdownDocument();
        md.save("Test content");
        String exported = md.exportContent();
        assertNotNull(exported);
        assertTrue(exported.contains("Test content"));
    }

    @Test
    @DisplayName("CarDocument: getCreatedAt should return timestamp")
    public void carDocument_GetCreatedAt_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        assertNotNull(pdf.getCreatedAt());
    }

    @Test
    @DisplayName("CarDocument: setCar with null should handle gracefully")
    public void carDocument_SetCar_Null_ShouldWork() {
        PDFDocument pdf = new PDFDocument();
        pdf.setCar(null);
        assertNull(pdf.getCar());
    }

    @Test
    @DisplayName("Generator: generateSpecification should work")
    public void generator_GenerateSpecification_ShouldWork() {
        CarDocumentGenerator generator = new CarDocumentGenerator();
        Car car = builder.setModel("Spec Test").build();

        String spec = generator.generateSpecification(car);

        assertNotNull(spec);
        assertTrue(spec.contains("VEHICLE SPECIFICATION"));
        assertTrue(spec.contains("Spec Test"));
        assertTrue(spec.contains("POWERTRAIN"));
        assertTrue(spec.contains("PRICING"));
    }

    @Test
    @DisplayName("Generator: generateSummary should work")
    public void generator_GenerateSummary_ShouldWork() {
        CarDocumentGenerator generator = new CarDocumentGenerator();
        Car car = builder.setModel("Summary Test").build();

        String summary = generator.generateSummary(car);

        assertNotNull(summary);
        assertTrue(summary.contains("VEHICLE SUMMARY"));
        assertTrue(summary.contains("Summary Test"));
    }

    @Test
    @DisplayName("Generator: generatePriceBreakdown should work")
    public void generator_GeneratePriceBreakdown_ShouldWork() {
        CarDocumentGenerator generator = new CarDocumentGenerator();
        Car car = builder
                .setModel("Price Test")
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addExteriorOption(ExteriorOption.SUNROOF)
                .addSafetyFeature(SafetyFeature.REAR_CAMERA)
                .build();

        String breakdown = generator.generatePriceBreakdown(car);

        assertNotNull(breakdown);
        assertTrue(breakdown.contains("PRICE BREAKDOWN"));
        assertTrue(breakdown.contains("BASE PRICE"));
        assertTrue(breakdown.contains("GRAND TOTAL"));
    }

    @Test
    @DisplayName("Generator: generateHTMLTable should work")
    public void generator_GenerateHTMLTable_ShouldWork() {
        CarDocumentGenerator generator = new CarDocumentGenerator();
        Car car = builder.setModel("HTML Test").build();

        String html = generator.generateHTMLTable(car);

        assertNotNull(html);
        assertTrue(html.contains("<table>"));
        assertTrue(html.contains("</table>"));
        assertTrue(html.contains("HTML Test"));
    }

    @Test
    @DisplayName("System: addCar should work")
    public void system_AddCar_ShouldWork() {
        Car car = builder.setModel("System Test").build();
        system.addCar(car);

        assertEquals(1, system.getInventory().size());
        assertTrue(system.getInventory().contains(car));
    }

    @Test
    @DisplayName("System: createCustomCar should work")
    public void system_CreateCustomCar_ShouldWork() {
        Car car = system.createCustomCar(builder.setModel("Custom System Car"));

        assertNotNull(car);
        assertEquals(1, system.getInventory().size());
    }

    @Test
    @DisplayName("System: createPresetCar economy should work")
    public void system_CreatePresetCar_Economy_ShouldWork() {
        Car car = system.createPresetCar("economy", "Economy System");
        assertNotNull(car);
        assertEquals(EngineType.V4, car.getEngine());
    }

    @Test
    @DisplayName("System: createPresetCar family should work")
    public void system_CreatePresetCar_Family_ShouldWork() {
        Car car = system.createPresetCar("family", "Family System");
        assertNotNull(car);
        assertEquals(EngineType.V6, car.getEngine());
    }

    @Test
    @DisplayName("System: createPresetCar sports should work")
    public void system_CreatePresetCar_Sports_ShouldWork() {
        Car car = system.createPresetCar("sports", "Sports System");
        assertNotNull(car);
        assertEquals(EngineType.V8, car.getEngine());
    }

    @Test
    @DisplayName("System: createPresetCar luxury should work")
    public void system_CreatePresetCar_Luxury_ShouldWork() {
        Car car = system.createPresetCar("luxury", "Luxury System");
        assertNotNull(car);
        assertEquals(EngineType.V8, car.getEngine());
    }

    @Test
    @DisplayName("System: createPresetCar electric should work")
    public void system_CreatePresetCar_Electric_ShouldWork() {
        Car car = system.createPresetCar("electric", "Electric System");
        assertNotNull(car);
        assertEquals(EngineType.ELECTRIC, car.getEngine());
    }

    @Test
    @DisplayName("System: createPresetCar unknown should throw exception")
    public void system_CreatePresetCar_Unknown_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> system.createPresetCar("unknown", "Unknown"));
    }

    @Test
    @DisplayName("System: createCarDocument PDF specification should work")
    public void system_CreateCarDocument_PDFSpec_ShouldWork() {
        Car car = system.createPresetCar("luxury", "Luxury Doc Test");
        Document doc = system.createCarDocument(car, "pdf", "specification");

        assertNotNull(doc);
        assertInstanceOf(PDFDocument.class, doc);
        assertTrue(doc.getContent().contains("VEHICLE SPECIFICATION"));
    }

    @Test
    @DisplayName("System: createCarDocument Word summary should work")
    public void system_CreateCarDocument_WordSummary_ShouldWork() {
        Car car = system.createPresetCar("family", "Family Doc Test");
        Document doc = system.createCarDocument(car, "word", "summary");

        assertNotNull(doc);
        assertInstanceOf(WordDocument.class, doc);
        assertTrue(doc.getContent().contains("VEHICLE SUMMARY"));
    }

    @Test
    @DisplayName("System: createCarDocument HTML price should work")
    public void system_CreateCarDocument_HTMLPrice_ShouldWork() {
        Car car = system.createPresetCar("sports", "Sports Doc Test");
        Document doc = system.createCarDocument(car, "html", "price");

        assertNotNull(doc);
        assertInstanceOf(HTMLDocument.class, doc);
        assertTrue(doc.getContent().contains("PRICE BREAKDOWN"));
    }

    @Test
    @DisplayName("System: createCarDocument Markdown table should work")
    public void system_CreateCarDocument_MarkdownTable_ShouldWork() {
        Car car = system.createPresetCar("economy", "Economy Doc Test");
        Document doc = system.createCarDocument(car, "markdown", "table");

        assertNotNull(doc);
        assertInstanceOf(MarkdownDocument.class, doc);
    }

    @Test
    @DisplayName("System: createDocumentsForAllCars should work")
    public void system_CreateDocumentsForAllCars_ShouldWork() {
        system.createPresetCar("economy", "Car 1");
        system.createPresetCar("family", "Car 2");
        system.createPresetCar("sports", "Car 3");

        List<Document> docs = system.createDocumentsForAllCars("pdf", "summary");

        assertEquals(3, docs.size());
    }

    @Test
    @DisplayName("System: getInventory should return unmodifiable list")
    public void system_GetInventory_ShouldBeUnmodifiable() {
        system.createPresetCar("economy", "Test");
        List<Car> inventory = system.getInventory();

        assertThrows(UnsupportedOperationException.class,
                () -> inventory.add(builder.build()));
    }

    @Test
    @DisplayName("System: getDocuments should return unmodifiable list")
    public void system_GetDocuments_ShouldBeUnmodifiable() {
        Car car = system.createPresetCar("economy", "Test");
        system.createCarDocument(car, "pdf", "summary");
        List<Document> docs = system.getDocuments();

        assertThrows(UnsupportedOperationException.class,
                () -> docs.add(new PDFDocument()));
    }

    @Test
    @DisplayName("System: findCarsByModel should work")
    public void system_FindCarsByModel_ShouldWork() {
        system.createPresetCar("economy", "Alpha Model");
        system.createPresetCar("family", "Beta Model");
        system.createPresetCar("sports", "Alpha Sport");

        List<Car> results = system.findCarsByModel("Alpha");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("System: findCarsByModel case insensitive")
    public void system_FindCarsByModel_CaseInsensitive() {
        system.createPresetCar("economy", "Test Model");

        List<Car> results = system.findCarsByModel("test");
        assertEquals(1, results.size());
    }

    @Test
    @DisplayName("System: findCarsByPriceRange should work")
    public void system_FindCarsByPriceRange_ShouldWork() {
        system.createPresetCar("economy", "Cheap");
        system.createPresetCar("luxury", "Expensive");

        List<Car> cheap = system.findCarsByPriceRange(0, 30000);
        List<Car> expensive = system.findCarsByPriceRange(80000, 200000);

        assertEquals(1, cheap.size());
        assertEquals(1, expensive.size());
    }

    @Test
    @DisplayName("System: findCarsByEngine should work")
    public void system_FindCarsByEngine_ShouldWork() {
        system.createPresetCar("economy", "V4 Car");
        system.createPresetCar("sports", "V8 Car 1");
        system.createPresetCar("luxury", "V8 Car 2");

        List<Car> v8Cars = system.findCarsByEngine(EngineType.V8);
        List<Car> v4Cars = system.findCarsByEngine(EngineType.V4);

        assertEquals(2, v8Cars.size());
        assertEquals(1, v4Cars.size());
    }

    @Test
    @DisplayName("System: getInventorySummary should work")
    public void system_GetInventorySummary_ShouldWork() {
        system.createPresetCar("economy", "Test 1");
        system.createPresetCar("family", "Test 2");

        String summary = system.getInventorySummary();

        assertNotNull(summary);
        assertTrue(summary.contains("INVENTORY SUMMARY"));
        assertTrue(summary.contains("Total Cars: 2"));
    }

    @Test
    @DisplayName("System: clearAll should work")
    public void system_ClearAll_ShouldWork() {
        system.createPresetCar("economy", "Test");
        Car car = system.getInventory().get(0);
        system.createCarDocument(car, "pdf", "summary");

        system.clearAll();

        assertEquals(0, system.getInventory().size());
        assertEquals(0, system.getDocuments().size());
    }

    @Test
    @DisplayName("CarOrder: constructor should work")
    public void carOrder_Constructor_ShouldWork() {
        Car car = builder.setModel("Order Test").build();
        CarOrder order = new CarOrder(car, "John Doe");

        assertNotNull(order.getOrderId());
        assertTrue(order.getOrderId().startsWith("ORD-"));
        assertEquals(car, order.getCar());
        assertEquals("John Doe", order.getCustomerName());
        assertEquals(CarOrder.OrderStatus.PENDING, order.getStatus());
        assertNotNull(order.getOrderDate());
    }

    @Test
    @DisplayName("CarOrder: confirmOrder should work")
    public void carOrder_ConfirmOrder_ShouldWork() {
        Car car = builder.build();
        CarOrder order = new CarOrder(car, "Test Customer");

        order.confirmOrder();

        assertEquals(CarOrder.OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    @DisplayName("CarOrder: cancelOrder from pending should work")
    public void carOrder_CancelOrder_FromPending_ShouldWork() {
        Car car = builder.build();
        CarOrder order = new CarOrder(car, "Test Customer");

        order.cancelOrder();

        assertEquals(CarOrder.OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    @DisplayName("CarOrder: cancelOrder from confirmed should work")
    public void carOrder_CancelOrder_FromConfirmed_ShouldWork() {
        Car car = builder.build();
        CarOrder order = new CarOrder(car, "Test Customer");
        order.confirmOrder();

        order.cancelOrder();

        assertEquals(CarOrder.OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    @DisplayName("CarOrder: addDocument should work")
    public void carOrder_AddDocument_ShouldWork() {
        Car car = builder.build();
        CarOrder order = new CarOrder(car, "Test Customer");
        Document doc = new PDFDocument();

        order.addDocument(doc);

        assertEquals(1, order.getOrderDocuments().size());
    }

    @Test
    @DisplayName("CarOrder: getOrderDocuments should be unmodifiable")
    public void carOrder_GetOrderDocuments_ShouldBeUnmodifiable() {
        Car car = builder.build();
        CarOrder order = new CarOrder(car, "Test Customer");
        List<Document> docs = order.getOrderDocuments();

        assertThrows(UnsupportedOperationException.class,
                () -> docs.add(new PDFDocument()));
    }

    @Test
    @DisplayName("CarOrder: generateOrderDocument should work")
    public void carOrder_GenerateOrderDocument_ShouldWork() {
        Car car = builder.setModel("Order Doc Test").build();
        CarOrder order = new CarOrder(car, "Test Customer");

        Document doc = order.generateOrderDocument("pdf");

        assertNotNull(doc);
        assertTrue(doc.getContent().contains("ORDER CONFIRMATION"));
        assertEquals(1, order.getOrderDocuments().size());
    }

    @Test
    @DisplayName("CarOrder: getOrderSummary should work")
    public void carOrder_GetOrderSummary_ShouldWork() {
        Car car = builder.setModel("Summary Test").build();
        CarOrder order = new CarOrder(car, "John Smith");

        String summary = order.getOrderSummary();

        assertNotNull(summary);
        assertTrue(summary.contains("ORDER CONFIRMATION"));
        assertTrue(summary.contains("John Smith"));
        assertTrue(summary.contains(order.getOrderId()));
    }

    @Test
    @DisplayName("CarOrder: unique order IDs")
    public void carOrder_UniqueOrderIds_ShouldWork() {
        Car car = builder.build();
        CarOrder order1 = new CarOrder(car, "Customer 1");
        CarOrder order2 = new CarOrder(car, "Customer 2");

        assertNotEquals(order1.getOrderId(), order2.getOrderId());
    }

    @Test
    @DisplayName("Integration: Full workflow - create car, document, order")
    public void integration_FullWorkflow_ShouldWork() {
        Car car = system.createCustomCar(
                builder
                        .setModel("Integration Test Car")
                        .setEngine(EngineType.V6)
                        .setTransmission(TransmissionType.AUTOMATIC_8_SPEED)
                        .setColor(ExteriorColor.BLUE)
                        .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                        .addExteriorOption(ExteriorOption.SUNROOF)
        );

        Document pdfSpec = system.createCarDocument(car, "pdf", "specification");
        Document htmlSummary = system.createCarDocument(car, "html", "summary");

        CarOrder order = new CarOrder(car, "Integration Customer");
        order.confirmOrder();
        order.generateOrderDocument("pdf");

        assertEquals(1, system.getInventory().size());
        assertEquals(2, system.getDocuments().size());
        assertEquals(CarOrder.OrderStatus.CONFIRMED, order.getStatus());
        assertEquals(1, order.getOrderDocuments().size());
    }

    @Test
    @DisplayName("Integration: Multiple preset cars with documents")
    public void integration_MultiplePresetCars_ShouldWork() {
        system.createPresetCar("economy", "Economy");
        system.createPresetCar("family", "Family");
        system.createPresetCar("sports", "Sports");
        system.createPresetCar("luxury", "Luxury");
        system.createPresetCar("electric", "Electric");

        List<Document> docs = system.createDocumentsForAllCars("pdf", "specification");

        assertEquals(5, system.getInventory().size());
        assertEquals(5, docs.size());
    }

    @Test
    @DisplayName("Integration: Search and generate documents")
    public void integration_SearchAndGenerateDocs_ShouldWork() {
        system.createPresetCar("sports", "Alpha Sports");
        system.createPresetCar("luxury", "Alpha Luxury");
        system.createPresetCar("economy", "Beta Economy");

        List<Car> alphaCars = system.findCarsByModel("Alpha");

        for (Car car : alphaCars) {
            system.createCarDocument(car, "pdf", "price");
        }

        assertEquals(2, alphaCars.size());
        assertEquals(2, system.getDocuments().size());
    }

    @Test
    @DisplayName("Integration: Document with car association")
    public void integration_DocumentWithCarAssociation_ShouldWork() {
        Car car = system.createPresetCar("luxury", "Associated Car");
        Document doc = system.createCarDocument(car, "pdf", "specification");

        assertInstanceOf(CarDocument.class, doc);
        CarDocument carDoc = (CarDocument) doc;

        assertEquals(car, carDoc.getCar());
        assertNotNull(carDoc.getTitle());
        assertTrue(carDoc.getTitle().contains("Associated Car"));
    }

    @Test
    @DisplayName("Edge Case: Empty inventory search")
    public void edgeCase_EmptyInventorySearch_ShouldReturnEmptyList() {
        List<Car> results = system.findCarsByModel("Nonexistent");
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Edge Case: Price range with no matches")
    public void edgeCase_PriceRangeNoMatches_ShouldReturnEmptyList() {
        system.createPresetCar("economy", "Cheap Car");
        List<Car> results = system.findCarsByPriceRange(1000000, 2000000);
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Edge Case: Car with all features")
    public void edgeCase_CarWithAllFeatures_ShouldWork() {
        for (InteriorFeature f : InteriorFeature.values()) {
            builder.addInteriorFeature(f);
        }
        for (ExteriorOption o : ExteriorOption.values()) {
            builder.addExteriorOption(o);
        }
        for (SafetyFeature s : SafetyFeature.values()) {
            builder.addSafetyFeature(s);
        }

        Car car = builder.setModel("Full Featured").build();

        assertEquals(InteriorFeature.values().length, car.getInteriorFeatures().size());
        assertEquals(ExteriorOption.values().length, car.getExteriorOptions().size());
        assertEquals(SafetyFeature.values().length, car.getSafetyFeatures().size());
    }

    @Test
    @DisplayName("Edge Case: Duplicate features should not duplicate")
    public void edgeCase_DuplicateFeatures_ShouldNotDuplicate() {
        builder.addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS);

        Car car = builder.build();

        long count = car.getInteriorFeatures().stream()
                .filter(f -> f == InteriorFeature.LEATHER_SEATS)
                .count();

        assertEquals(1, count);
    }

    @Test
    @DisplayName("Edge Case: Very long model name")
    public void edgeCase_VeryLongModelName_ShouldWork() {
        String longName = "A".repeat(1000);
        Car car = builder.setModel(longName).build();
        assertEquals(longName, car.getModel());
    }

    @Test
    @DisplayName("Edge Case: Special characters in model name")
    public void edgeCase_SpecialCharactersInModelName_ShouldWork() {
        String specialName = "Model-X 2.0 (Sport Edition) @#$%";
        Car car = builder.setModel(specialName).build();
        assertEquals(specialName, car.getModel());
    }

    @Test
    @DisplayName("Edge Case: Unicode in model name")
    public void edgeCase_UnicodeInModelName_ShouldWork() {
        String unicodeName = "モデル テスト 🚗";
        Car car = builder.setModel(unicodeName).build();
        assertEquals(unicodeName, car.getModel());
    }

    @Test
    @DisplayName("Edge Case: Zero base price")
    public void edgeCase_ZeroBasePrice_ShouldWork() {
        Car car = builder.setBasePrice(0.0).build();
        assertEquals(0.0, car.getBasePrice(), 0.01);
    }

    @Test
    @DisplayName("Edge Case: Very high base price")
    public void edgeCase_VeryHighBasePrice_ShouldWork() {
        Car car = builder.setBasePrice(10000000.0).build();
        assertEquals(10000000.0, car.getBasePrice(), 0.01);
    }

    static Stream<Arguments> presetCarTypeProvider() {
        return Stream.of(
                Arguments.of("economy", EngineType.V4),
                Arguments.of("family", EngineType.V6),
                Arguments.of("sports", EngineType.V8),
                Arguments.of("luxury", EngineType.V8),
                Arguments.of("electric", EngineType.ELECTRIC)
        );
    }

    @ParameterizedTest
    @MethodSource("presetCarTypeProvider")
    @DisplayName("Parameterized: Preset car types")
    public void parameterized_PresetCarTypes(String type, EngineType expectedEngine) {
        Car car = system.createPresetCar(type, "Test " + type);
        assertEquals(expectedEngine, car.getEngine());
    }

    static Stream<Arguments> documentTypeProvider() {
        return Stream.of(
                Arguments.of("pdf", PDFDocument.class),
                Arguments.of("word", WordDocument.class),
                Arguments.of("html", HTMLDocument.class),
                Arguments.of("markdown", MarkdownDocument.class)
        );
    }

    @ParameterizedTest
    @MethodSource("documentTypeProvider")
    @DisplayName("Parameterized: Document types")
    public void parameterized_DocumentTypes(String type, Class<?> expectedClass) {
        Document doc = DocumentFactory.createDocument(type);
        assertTrue(expectedClass.isInstance(doc));
    }

    static Stream<Arguments> contentTypeProvider() {
        return Stream.of(
                Arguments.of("specification", "VEHICLE SPECIFICATION"),
                Arguments.of("spec", "VEHICLE SPECIFICATION"),
                Arguments.of("summary", "VEHICLE SUMMARY"),
                Arguments.of("price", "PRICE BREAKDOWN"),
                Arguments.of("pricing", "PRICE BREAKDOWN")
        );
    }

    @ParameterizedTest
    @MethodSource("contentTypeProvider")
    @DisplayName("Parameterized: Content types")
    public void parameterized_ContentTypes(String contentType, String expectedContent) {
        Car car = system.createPresetCar("economy", "Test");
        Document doc = system.createCarDocument(car, "pdf", contentType);
        assertTrue(doc.getContent().contains(expectedContent));
    }

    @ParameterizedTest
    @EnumSource(EngineType.class)
    @DisplayName("Parameterized: All engine types settable")
    public void parameterized_AllEngineTypes(EngineType engine) {
        if (engine == EngineType.ELECTRIC) {
            builder.setTransmission(TransmissionType.CVT);
        }
        builder.setEngine(engine);
        Car car = builder.build();
        assertEquals(engine, car.getEngine());
    }

    @ParameterizedTest
    @EnumSource(ExteriorColor.class)
    @DisplayName("Parameterized: All colors settable")
    public void parameterized_AllColors(ExteriorColor color) {
        builder.setColor(color);
        Car car = builder.build();
        assertEquals(color, car.getColor());
    }

    @ParameterizedTest
    @EnumSource(RimType.class)
    @DisplayName("Parameterized: All rim types settable")
    public void parameterized_AllRimTypes(RimType rim) {
        builder.setRims(rim);
        Car car = builder.build();
        assertEquals(rim, car.getRims());
    }
}
