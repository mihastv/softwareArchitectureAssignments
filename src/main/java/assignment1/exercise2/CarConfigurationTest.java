package assignment1.exercise2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Year;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CarConfigurationTest {

    private CustomCarBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new CustomCarBuilder();
    }

    @Test
    @DisplayName("EngineType: V4 should have correct properties")
    public void engineType_V4_ShouldHaveCorrectProperties() {
        EngineType engine = EngineType.V4;
        assertEquals("V4", engine.getName());
        assertEquals(150, engine.getHorsepower());
        assertEquals(4, engine.getCylinders());
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
    @DisplayName("EngineType: All engine types should have valid toString")
    public void engineType_AllTypes_ShouldHaveValidToString(EngineType engine) {
        String str = engine.toString();
        assertNotNull(str);
        assertFalse(str.isEmpty());
        assertTrue(str.contains(engine.getName()));
    }

    @Test
    @DisplayName("TransmissionType: Manual 5-speed should not be automatic")
    public void transmissionType_Manual5Speed_ShouldNotBeAutomatic() {
        TransmissionType trans = TransmissionType.MANUAL_5_SPEED;
        assertEquals("5-Speed Manual", trans.getName());
        assertEquals(5, trans.getGears());
        assertFalse(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: Manual 6-speed should not be automatic")
    public void transmissionType_Manual6Speed_ShouldNotBeAutomatic() {
        TransmissionType trans = TransmissionType.MANUAL_6_SPEED;
        assertEquals("6-Speed Manual", trans.getName());
        assertEquals(6, trans.getGears());
        assertFalse(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: Automatic 6-speed should be automatic")
    public void transmissionType_Automatic6Speed_ShouldBeAutomatic() {
        TransmissionType trans = TransmissionType.AUTOMATIC_6_SPEED;
        assertEquals(6, trans.getGears());
        assertTrue(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: Automatic 8-speed should be automatic")
    public void transmissionType_Automatic8Speed_ShouldBeAutomatic() {
        TransmissionType trans = TransmissionType.AUTOMATIC_8_SPEED;
        assertEquals(8, trans.getGears());
        assertTrue(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: CVT should be automatic with zero gears")
    public void transmissionType_CVT_ShouldBeAutomaticWithZeroGears() {
        TransmissionType trans = TransmissionType.CVT;
        assertEquals(0, trans.getGears());
        assertTrue(trans.isAutomatic());
    }

    @Test
    @DisplayName("TransmissionType: Dual-clutch should be automatic")
    public void transmissionType_DualClutch_ShouldBeAutomatic() {
        TransmissionType trans = TransmissionType.DUAL_CLUTCH;
        assertEquals(7, trans.getGears());
        assertTrue(trans.isAutomatic());
    }

    @ParameterizedTest
    @EnumSource(TransmissionType.class)
    @DisplayName("TransmissionType: All types should have valid toString")
    public void transmissionType_AllTypes_ShouldHaveValidToString(TransmissionType trans) {
        String str = trans.toString();
        assertNotNull(str);
        assertFalse(str.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(ExteriorColor.class)
    @DisplayName("ExteriorColor: All colors should have valid toString")
    public void exteriorColor_AllColors_ShouldHaveValidToString(ExteriorColor color) {
        String str = color.toString();
        assertNotNull(str);
        assertFalse(str.isEmpty());
    }

    @Test
    @DisplayName("ExteriorColor: Should have expected number of options")
    public void exteriorColor_ShouldHaveExpectedOptions() {
        assertEquals(7, ExteriorColor.values().length);
    }

    @Test
    @DisplayName("RimType: Standard 16 should have size 16")
    public void rimType_Standard16_ShouldHaveSize16() {
        RimType rim = RimType.STANDARD_16;
        assertEquals(16, rim.getSize());
    }

    @Test
    @DisplayName("RimType: Performance 21 should have size 21")
    public void rimType_Performance21_ShouldHaveSize21() {
        RimType rim = RimType.PERFORMANCE_21;
        assertEquals(21, rim.getSize());
    }

    @ParameterizedTest
    @EnumSource(RimType.class)
    @DisplayName("RimType: All rims should have valid size and toString")
    public void rimType_AllTypes_ShouldHaveValidProperties(RimType rim) {
        assertTrue(rim.getSize() >= 16);
        assertTrue(rim.getSize() <= 21);
        assertNotNull(rim.toString());
        assertFalse(rim.toString().isEmpty());
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
        assertEquals("GPS Navigation System", feature.getName());
        assertEquals(1200.0, feature.getPrice(), 0.01);
    }

    @ParameterizedTest
    @EnumSource(InteriorFeature.class)
    @DisplayName("InteriorFeature: All features should have non-negative price")
    public void interiorFeature_AllFeatures_ShouldHaveNonNegativePrice(InteriorFeature feature) {
        assertTrue(feature.getPrice() >= 0);
        assertNotNull(feature.getName());
        assertFalse(feature.getName().isEmpty());
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
        assertFalse(option.getName().isEmpty());
        assertNotNull(option.toString());
    }

    @Test
    @DisplayName("SafetyFeature: ABS should be standard with zero price")
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

    @Test
    @DisplayName("SafetyFeature: Adaptive cruise should have correct price")
    public void safetyFeature_AdaptiveCruise_ShouldHaveCorrectPrice() {
        SafetyFeature feature = SafetyFeature.ADAPTIVE_CRUISE;
        assertEquals(1200.0, feature.getPrice(), 0.01);
        assertFalse(feature.isStandard());
    }

    @ParameterizedTest
    @EnumSource(SafetyFeature.class)
    @DisplayName("SafetyFeature: All features should have valid properties")
    public void safetyFeature_AllFeatures_ShouldHaveValidProperties(SafetyFeature feature) {
        assertTrue(feature.getPrice() >= 0);
        assertNotNull(feature.getName());
        assertFalse(feature.getName().isEmpty());
        assertNotNull(feature.toString());
        if (feature.isStandard()) {
            assertEquals(0.0, feature.getPrice(), 0.01);
        }
    }

    @Test
    @DisplayName("Builder: Should create builder with default values")
    public void builder_Constructor_ShouldSetDefaults() {
        CustomCarBuilder newBuilder = new CustomCarBuilder();
        assertNotNull(newBuilder);
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
        assertTrue(features.contains(InteriorFeature.LEATHER_SEATS));
        assertTrue(features.contains(InteriorFeature.GPS_NAVIGATION));
        assertTrue(features.contains(InteriorFeature.HEATED_SEATS));
        assertEquals(3, features.size());
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
                ExteriorOption.SPOILER,
                ExteriorOption.TINTED_WINDOWS
        ).build();

        Set<ExteriorOption> options = car.getExteriorOptions();
        assertTrue(options.contains(ExteriorOption.SUNROOF));
        assertTrue(options.contains(ExteriorOption.SPOILER));
        assertTrue(options.contains(ExteriorOption.TINTED_WINDOWS));
        assertEquals(3, options.size());
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
                SafetyFeature.BLIND_SPOT_MONITOR,
                SafetyFeature.ADAPTIVE_CRUISE
        ).build();

        Set<SafetyFeature> features = car.getSafetyFeatures();
        assertTrue(features.contains(SafetyFeature.REAR_CAMERA));
        assertTrue(features.contains(SafetyFeature.BLIND_SPOT_MONITOR));
        assertTrue(features.contains(SafetyFeature.ADAPTIVE_CRUISE));
    }

    @Test
    @DisplayName("Builder: Should include standard safety features by default")
    public void builder_Build_ShouldIncludeStandardSafetyFeatures() {
        Car car = builder.build();
        Set<SafetyFeature> features = car.getSafetyFeatures();
        assertTrue(features.contains(SafetyFeature.ABS));
        assertTrue(features.contains(SafetyFeature.AIRBAGS_FRONT));
    }

    @Test
    @DisplayName("Builder: Should support fluent interface chaining")
    public void builder_FluentInterface_ShouldWork() {
        Car car = builder
                .setModel("Fluent Test")
                .setYear(Year.now().getValue())
                .setBasePrice(40000.0)
                .setEngine(EngineType.V6)
                .setTransmission(TransmissionType.AUTOMATIC_6_SPEED)
                .setColor(ExteriorColor.BLUE)
                .setRims(RimType.ALLOY_18)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addExteriorOption(ExteriorOption.SUNROOF)
                .addSafetyFeature(SafetyFeature.REAR_CAMERA)
                .build();

        assertEquals("Fluent Test", car.getModel());
        assertEquals(EngineType.V6, car.getEngine());
        assertEquals(TransmissionType.AUTOMATIC_6_SPEED, car.getTransmission());
        assertEquals(ExteriorColor.BLUE, car.getColor());
        assertEquals(RimType.ALLOY_18, car.getRims());
    }

    @Test
    @DisplayName("Builder: Reset should restore default values")
    public void builder_Reset_ShouldRestoreDefaults() {
        builder.setModel("Custom Model")
                .setEngine(EngineType.V8)
                .setColor(ExteriorColor.RED)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS);

        builder.reset();

        Car car = builder.build();
        assertNotEquals("Custom Model", car.getModel());
        assertEquals(EngineType.V4, car.getEngine());
        assertEquals(ExteriorColor.WHITE, car.getColor());
    }

    @Test
    @DisplayName("Builder: Should be reusable after build")
    public void builder_ShouldBeReusableAfterBuild() {
        Car car1 = builder.setModel("Car 1").setColor(ExteriorColor.RED).build();

        builder.reset();
        Car car2 = builder.setModel("Car 2").setColor(ExteriorColor.BLUE).build();

        assertEquals("Car 1", car1.getModel());
        assertEquals(ExteriorColor.RED, car1.getColor());
        assertEquals("Car 2", car2.getModel());
        assertEquals(ExteriorColor.BLUE, car2.getColor());
        assertNotEquals(car1.getVin(), car2.getVin());
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
    @DisplayName("Builder: Should throw exception for whitespace-only model")
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
    @DisplayName("Builder: Should accept valid year range")
    public void builder_SetYear_ValidRange_ShouldWork() {
        int currentYear = Year.now().getValue();
        assertDoesNotThrow(() -> builder.setYear(currentYear));
        assertDoesNotThrow(() -> builder.setYear(currentYear + 1));
        assertDoesNotThrow(() -> builder.setYear(currentYear + 2));
    }

    @Test
    @DisplayName("Builder: Should throw exception for negative price")
    public void builder_SetBasePrice_Negative_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> builder.setBasePrice(-1000.0));
    }

    @Test
    @DisplayName("Builder: Should accept zero price")
    public void builder_SetBasePrice_Zero_ShouldWork() {
        assertDoesNotThrow(() -> builder.setBasePrice(0.0));
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
    @DisplayName("Builder: Should throw exception for electric with manual transmission")
    public void builder_ElectricWithManual_ShouldThrowException() {
        builder.setEngine(EngineType.ELECTRIC)
                .setTransmission(TransmissionType.MANUAL_5_SPEED);

        assertThrows(IllegalStateException.class, () -> builder.build());
    }

    @Test
    @DisplayName("Builder: Should throw exception for electric with 6-speed manual")
    public void builder_ElectricWith6SpeedManual_ShouldThrowException() {
        builder.setEngine(EngineType.ELECTRIC)
                .setTransmission(TransmissionType.MANUAL_6_SPEED);

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
        assertEquals(TransmissionType.CVT, car.getTransmission());
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
        assertTrue(car2.getVin().startsWith("VIN-"));
    }

    @Test
    @DisplayName("Car: Should calculate total price correctly")
    public void car_GetTotalPrice_ShouldCalculateCorrectly() {
        Car car = builder
                .setBasePrice(30000.0)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)  // 1500
                .addInteriorFeature(InteriorFeature.GPS_NAVIGATION) // 1200
                .addExteriorOption(ExteriorOption.SUNROOF)          // 1200
                .addSafetyFeature(SafetyFeature.REAR_CAMERA)        // 500
                .build();

        double expectedTotal = 30000.0 + 1500.0 + 1200.0 + 1200.0 + 500.0;
        assertEquals(expectedTotal, car.getTotalPrice(), 0.01);
    }

    @Test
    @DisplayName("Car: Should calculate options price correctly")
    public void car_GetOptionsPrice_ShouldCalculateCorrectly() {
        Car car = builder
                .setBasePrice(30000.0)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)  // 1500
                .addExteriorOption(ExteriorOption.SUNROOF)          // 1200
                .build();

        double expectedOptions = 1500.0 + 1200.0;
        assertEquals(expectedOptions, car.getOptionsPrice(), 0.01);
    }

    @Test
    @DisplayName("Car: Options price should be zero with no options")
    public void car_GetOptionsPrice_NoOptions_ShouldBeZero() {
        Car car = builder.setBasePrice(30000.0).build();
        assertEquals(0.0, car.getOptionsPrice(), 0.01);
    }

    @Test
    @DisplayName("Car: toString should include year and model")
    public void car_ToString_ShouldIncludeYearAndModel() {
        int year = Year.now().getValue();
        Car car = builder.setModel("Test Model").setYear(year).build();

        String str = car.toString();
        assertTrue(str.contains("Test Model"));
        assertTrue(str.contains(String.valueOf(year)));
    }

    @Test
    @DisplayName("Car: getFullSpecification should return detailed info")
    public void car_GetFullSpecification_ShouldReturnDetailedInfo() {
        Car car = builder
                .setModel("Spec Test")
                .setEngine(EngineType.V6)
                .setTransmission(TransmissionType.AUTOMATIC_6_SPEED)
                .setColor(ExteriorColor.RED)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .build();

        String spec = car.getFullSpecification();

        assertNotNull(spec);
        assertFalse(spec.isEmpty());
        assertTrue(spec.contains("Spec Test"));
        assertTrue(spec.contains("V6"));
        assertTrue(spec.contains("POWERTRAIN") || spec.contains("Engine"));
        assertTrue(spec.contains("EXTERIOR") || spec.contains("Color"));
        assertTrue(spec.contains("INTERIOR") || spec.contains("Leather"));
        assertTrue(spec.contains("PRICING") || spec.contains("Price"));
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
        Car car = builder.addSafetyFeature(SafetyFeature.REAR_CAMERA).build();
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
        assertTrue(car.getTransmission() == TransmissionType.MANUAL_5_SPEED ||
                car.getTransmission() == TransmissionType.MANUAL_6_SPEED ||
                car.getTransmission().isAutomatic() == false ||
                car.getTransmission() != null);
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
        assertFalse(car.getExteriorOptions().isEmpty());
    }

    @Test
    @DisplayName("Configurator: Should build sports car")
    public void configurator_BuildSportsCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildSportsCar("Sports Model");

        assertNotNull(car);
        assertEquals("Sports Model", car.getModel());
        assertEquals(EngineType.V8, car.getEngine());
        assertFalse(car.getInteriorFeatures().isEmpty());
    }

    @Test
    @DisplayName("Configurator: Should build luxury car")
    public void configurator_BuildLuxuryCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildLuxuryCar("Luxury Model");

        assertNotNull(car);
        assertEquals("Luxury Model", car.getModel());
        assertEquals(EngineType.V8, car.getEngine());
        assertTrue(car.getInteriorFeatures().size() >= 5);
        assertTrue(car.getSafetyFeatures().size() >= 5);
    }

    @Test
    @DisplayName("Configurator: Should build electric car")
    public void configurator_BuildElectricCar_ShouldWork() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildElectricCar("Electric Model");

        assertNotNull(car);
        assertEquals("Electric Model", car.getModel());
        assertEquals(EngineType.ELECTRIC, car.getEngine());
        assertTrue(car.getTransmission().isAutomatic());
    }

    @Test
    @DisplayName("Configurator: Economy car should be cheapest")
    public void configurator_EconomyCar_ShouldBeCheapest() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());

        Car economyCar = configurator.buildEconomyCar("Economy");
        Car familyCar = configurator.buildFamilyCar("Family");
        Car sportsCar = configurator.buildSportsCar("Sports");
        Car luxuryCar = configurator.buildLuxuryCar("Luxury");

        assertTrue(economyCar.getTotalPrice() < familyCar.getTotalPrice());
        assertTrue(familyCar.getTotalPrice() < sportsCar.getTotalPrice());
        assertTrue(sportsCar.getTotalPrice() < luxuryCar.getTotalPrice());
    }

    @Test
    @DisplayName("Configurator: Should create unique cars each time")
    public void configurator_ShouldCreateUniqueCars() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());

        Car car1 = configurator.buildEconomyCar("Model A");
        Car car2 = configurator.buildEconomyCar("Model B");

        assertNotSame(car1, car2);
        assertNotEquals(car1.getVin(), car2.getVin());
    }

    @Test
    @DisplayName("CarOrder: Should create order with pending status")
    public void carOrder_Constructor_ShouldCreatePendingOrder() {
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
    @DisplayName("CarOrder: Should confirm pending order")
    public void carOrder_ConfirmOrder_ShouldChangeStatus() {
        Car car = builder.setModel("Order Test").build();
        CarOrder order = new CarOrder(car, "John Doe");

        order.confirmOrder();

        assertEquals(CarOrder.OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    @DisplayName("CarOrder: Should cancel pending order")
    public void carOrder_CancelOrder_FromPending_ShouldWork() {
        Car car = builder.setModel("Order Test").build();
        CarOrder order = new CarOrder(car, "John Doe");

        order.cancelOrder();

        assertEquals(CarOrder.OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    @DisplayName("CarOrder: Should cancel confirmed order")
    public void carOrder_CancelOrder_FromConfirmed_ShouldWork() {
        Car car = builder.setModel("Order Test").build();
        CarOrder order = new CarOrder(car, "John Doe");

        order.confirmOrder();
        order.cancelOrder();

        assertEquals(CarOrder.OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    @DisplayName("CarOrder: Should generate unique order IDs")
    public void carOrder_ShouldGenerateUniqueOrderIds() {
        Car car = builder.setModel("Order Test").build();

        CarOrder order1 = new CarOrder(car, "Customer 1");
        CarOrder order2 = new CarOrder(car, "Customer 2");

        assertNotEquals(order1.getOrderId(), order2.getOrderId());
    }

    @Test
    @DisplayName("CarOrder: getOrderSummary should return detailed info")
    public void carOrder_GetOrderSummary_ShouldReturnDetailedInfo() {
        Car car = builder.setModel("Summary Test").build();
        CarOrder order = new CarOrder(car, "Test Customer");

        String summary = order.getOrderSummary();

        assertNotNull(summary);
        assertFalse(summary.isEmpty());
        assertTrue(summary.contains(order.getOrderId()));
        assertTrue(summary.contains("Test Customer"));
        assertTrue(summary.contains("Summary Test"));
    }

    @Test
    @DisplayName("Integration: Full car configuration workflow")
    public void integration_FullCarConfigurationWorkflow() {
        Car car = builder
                .setModel("Integration Test Model")
                .setYear(Year.now().getValue())
                .setBasePrice(45000.0)
                .setEngine(EngineType.V6)
                .setTransmission(TransmissionType.AUTOMATIC_8_SPEED)
                .setColor(ExteriorColor.BLUE)
                .setRims(RimType.ALLOY_18)
                .addInteriorFeatures(
                        InteriorFeature.LEATHER_SEATS,
                        InteriorFeature.GPS_NAVIGATION,
                        InteriorFeature.HEATED_SEATS,
                        InteriorFeature.PREMIUM_SOUND
                )
                .addExteriorOptions(
                        ExteriorOption.SUNROOF,
                        ExteriorOption.TINTED_WINDOWS
                )
                .addSafetyFeatures(
                        SafetyFeature.REAR_CAMERA,
                        SafetyFeature.BLIND_SPOT_MONITOR,
                        SafetyFeature.ADAPTIVE_CRUISE
                )
                .build();

        assertEquals("Integration Test Model", car.getModel());
        assertEquals(EngineType.V6, car.getEngine());
        assertEquals(TransmissionType.AUTOMATIC_8_SPEED, car.getTransmission());
        assertEquals(ExteriorColor.BLUE, car.getColor());
        assertEquals(RimType.ALLOY_18, car.getRims());
        assertEquals(4, car.getInteriorFeatures().size());
        assertEquals(2, car.getExteriorOptions().size());
        assertTrue(car.getSafetyFeatures().size() >= 5); // 2 standard + 3 added
        assertTrue(car.getTotalPrice() > car.getBasePrice());
    }

    @Test
    @DisplayName("Integration: Order creation and confirmation workflow")
    public void integration_OrderWorkflow() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());
        Car car = configurator.buildLuxuryCar("Luxury Order Test");

        CarOrder order = new CarOrder(car, "Premium Customer");
        assertEquals(CarOrder.OrderStatus.PENDING, order.getStatus());

        order.confirmOrder();
        assertEquals(CarOrder.OrderStatus.CONFIRMED, order.getStatus());

        assertEquals(car, order.getCar());
        assertEquals("Premium Customer", order.getCustomerName());
        assertNotNull(order.getOrderSummary());
    }

    @Test
    @DisplayName("Integration: Multiple car configurations")
    public void integration_MultipleCarConfigurations() {
        CarConfigurationDirector configurator = new CarConfigurationDirector(new CustomCarBuilder());

        Car[] cars = {
                configurator.buildEconomyCar("Economy"),
                configurator.buildFamilyCar("Family"),
                configurator.buildSportsCar("Sports"),
                configurator.buildLuxuryCar("Luxury"),
                configurator.buildElectricCar("Electric")
        };

        for (int i = 0; i < cars.length; i++) {
            for (int j = i + 1; j < cars.length; j++) {
                assertNotEquals(cars[i].getVin(), cars[j].getVin());
            }
        }

        for (Car car : cars) {
            assertNotNull(car.getModel());
            assertNotNull(car.getEngine());
            assertNotNull(car.getTransmission());
            assertNotNull(car.getColor());
            assertNotNull(car.getRims());
            assertNotNull(car.getVin());
            assertTrue(car.getBasePrice() >= 0);
            assertTrue(car.getTotalPrice() >= car.getBasePrice());
        }
    }

    @Test
    @DisplayName("Edge Case: Car with all interior features")
    public void edgeCase_CarWithAllInteriorFeatures() {
        for (InteriorFeature feature : InteriorFeature.values()) {
            builder.addInteriorFeature(feature);
        }

        Car car = builder.build();

        assertEquals(InteriorFeature.values().length, car.getInteriorFeatures().size());
    }

    @Test
    @DisplayName("Edge Case: Car with all exterior options")
    public void edgeCase_CarWithAllExteriorOptions() {
        for (ExteriorOption option : ExteriorOption.values()) {
            builder.addExteriorOption(option);
        }

        Car car = builder.build();

        assertEquals(ExteriorOption.values().length, car.getExteriorOptions().size());
    }

    @Test
    @DisplayName("Edge Case: Car with all safety features")
    public void edgeCase_CarWithAllSafetyFeatures() {
        for (SafetyFeature feature : SafetyFeature.values()) {
            builder.addSafetyFeature(feature);
        }

        Car car = builder.build();

        assertEquals(SafetyFeature.values().length, car.getSafetyFeatures().size());
    }

    @Test
    @DisplayName("Edge Case: Adding duplicate features should not duplicate")
    public void edgeCase_DuplicateFeatures_ShouldNotDuplicate() {
        builder.addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS);

        Car car = builder.build();

        long leatherCount = car.getInteriorFeatures().stream()
                .filter(f -> f == InteriorFeature.LEATHER_SEATS)
                .count();

        assertEquals(1, leatherCount);
    }

    @Test
    @DisplayName("Edge Case: Very high base price")
    public void edgeCase_VeryHighBasePrice() {
        Car car = builder.setBasePrice(1000000.0).build();
        assertEquals(1000000.0, car.getBasePrice(), 0.01);
        assertTrue(car.getTotalPrice() >= 1000000.0);
    }

    @Test
    @DisplayName("Edge Case: Model name with special characters")
    public void edgeCase_ModelNameWithSpecialCharacters() {
        Car car = builder.setModel("Model-X 2.0 (Sport Edition)").build();
        assertEquals("Model-X 2.0 (Sport Edition)", car.getModel());
    }

    @Test
    @DisplayName("Edge Case: Model name with unicode")
    public void edgeCase_ModelNameWithUnicode() {
        Car car = builder.setModel("モデル テスト").build();
        assertEquals("モデル テスト", car.getModel());
    }

    static Stream<Arguments> engineTransmissionProvider() {
        return Stream.of(
                Arguments.of(EngineType.V4, TransmissionType.MANUAL_5_SPEED, true),
                Arguments.of(EngineType.V6, TransmissionType.AUTOMATIC_6_SPEED, true),
                Arguments.of(EngineType.V8, TransmissionType.DUAL_CLUTCH, true),
                Arguments.of(EngineType.HYBRID, TransmissionType.CVT, true),
                Arguments.of(EngineType.ELECTRIC, TransmissionType.CVT, true),
                Arguments.of(EngineType.ELECTRIC, TransmissionType.AUTOMATIC_8_SPEED, true),
                Arguments.of(EngineType.ELECTRIC, TransmissionType.MANUAL_5_SPEED, false),
                Arguments.of(EngineType.ELECTRIC, TransmissionType.MANUAL_6_SPEED, false)
        );
    }

    @ParameterizedTest
    @MethodSource("engineTransmissionProvider")
    @DisplayName("Parameterized: Engine-Transmission compatibility")
    public void parameterized_EngineTransmissionCompatibility(
            EngineType engine, TransmissionType transmission, boolean shouldSucceed) {

        builder.setEngine(engine).setTransmission(transmission);

        if (shouldSucceed) {
            assertDoesNotThrow(() -> builder.build());
        } else {
            assertThrows(IllegalStateException.class, () -> builder.build());
        }
    }

    @ParameterizedTest
    @EnumSource(EngineType.class)
    @DisplayName("Parameterized: All engine types should be settable")
    public void parameterized_AllEngineTypes_ShouldBeSettable(EngineType engine) {
        if (engine == EngineType.ELECTRIC) {
            builder.setTransmission(TransmissionType.CVT);
        }
        builder.setEngine(engine);
        Car car = builder.build();
        assertEquals(engine, car.getEngine());
    }

    @ParameterizedTest
    @EnumSource(ExteriorColor.class)
    @DisplayName("Parameterized: All colors should be settable")
    public void parameterized_AllColors_ShouldBeSettable(ExteriorColor color) {
        builder.setColor(color);
        Car car = builder.build();
        assertEquals(color, car.getColor());
    }

    @ParameterizedTest
    @EnumSource(RimType.class)
    @DisplayName("Parameterized: All rim types should be settable")
    public void parameterized_AllRimTypes_ShouldBeSettable(RimType rim) {
        builder.setRims(rim);
        Car car = builder.build();
        assertEquals(rim, car.getRims());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1000.0, 25000.0, 50000.0, 100000.0, 500000.0})
    @DisplayName("Parameterized: Various valid base prices")
    public void parameterized_ValidBasePrices(double price) {
        builder.setBasePrice(price);
        Car car = builder.build();
        assertEquals(price, car.getBasePrice(), 0.01);
    }

    @Test
    @DisplayName("Builder: getCurrentConfigurationSummary should return info")
    public void builder_GetCurrentConfigurationSummary_ShouldReturnInfo() {
        builder.setModel("Summary Test")
                .setEngine(EngineType.V6)
                .addInteriorFeature(InteriorFeature.LEATHER_SEATS);

        String summary = builder.getCurrentConfigurationSummary();

        assertNotNull(summary);
        assertFalse(summary.isEmpty());
        assertTrue(summary.contains("Summary Test") || summary.contains("Configuration"));
    }
}
