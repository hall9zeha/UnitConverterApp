package com.barryzea.simpleadmob.common.dataSource;

import static com.barryzea.simpleadmob.common.Constants.*;
import static com.barryzea.simpleadmob.common.Constants.ANGLO_UNIT;


import android.content.Context;

import com.barryzea.simpleadmob.R;
import com.barryzea.simpleadmob.common.entities.Measure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public class DummyDS {

    public static List<Measure>getUnitList(Context ctx,int optionPopulate){
        List<Measure> measureList = new ArrayList<>();
        switch(optionPopulate){
            case LENGTH:
                measureList= new ArrayList<>(Arrays.asList(
                        //todos los factores están normalizados al sistema internacional
                        new Measure("km",ctx.getString(R.string.kilometer),IS_UNIT,1000),
                        new Measure("m",ctx.getString(R.string.meter),IS_UNIT,1),//usamos metro como base
                        new Measure("cm",ctx.getString(R.string.centimeter),IS_UNIT,0.01),
                        new Measure("mm",ctx.getString(R.string.millimeter),IS_UNIT,0.001),
                        new Measure("nm",ctx.getString(R.string.nanometer),IS_UNIT,1e-9),
                        new Measure("dm",ctx.getString(R.string.decimeter),IS_UNIT,0.1),
                        new Measure("legua",ctx.getString(R.string.league),ANGLO_UNIT,5556),//metros
                        new Measure("mi",ctx.getString(R.string.nauticalMile),ANGLO_UNIT,1852),
                        new Measure("mi",ctx.getString(R.string.mile),ANGLO_UNIT,1609.34),
                        new Measure("yd",ctx.getString(R.string.yard),ANGLO_UNIT,0.9144),
                        new Measure("ft",ctx.getString(R.string.foot),ANGLO_UNIT,0.3048),
                        new Measure("in",ctx.getString(R.string.inch),ANGLO_UNIT,0.0254),
                        new Measure("ly",ctx.getString(R.string.lightSpeed),0,9.46055e15)
                ));
                break;
            case TEMPERATURE:
                measureList= new ArrayList<>(Arrays.asList(
                        new Measure("C°",ctx.getString(R.string.celsius),CELSIUS,0),
                        new Measure("F°",ctx.getString(R.string.fahrenheit),FAHRENHEIT,0),
                        new Measure("K°",ctx.getString(R.string.kelvin),KELVIN,0)
                ));
                break;
            case TIME:
                measureList= new ArrayList<>(Arrays.asList(
                        new Measure("yy",ctx.getString(R.string.year),AGE,31536000),
                        new Measure("M",ctx.getString(R.string.month),MONTH,2628000),
                        new Measure("week",ctx.getString(R.string.week),WEEK, 604800),
                        new Measure("day",ctx.getString(R.string.day),DAY,86400),
                        new Measure("h",ctx.getString(R.string.hour),HOUR,3600),
                        new Measure("min",ctx.getString(R.string.minute),MINUTE,60),
                        new Measure("s",ctx.getString(R.string.second),SECOND,1),//tomamos el segundo como base
                        new Measure("ms",ctx.getString(R.string.millisecond),MILLISECOND,0.001)
                ));
                break;
            case MASS:
                measureList= new ArrayList<>(Arrays.asList(
                        new Measure("t",ctx.getString(R.string.ton),IS_UNIT,1000000),
                        new Measure("kg",ctx.getString(R.string.kilogram),IS_UNIT,1000),
                        new Measure("g",ctx.getString(R.string.gram),IS_UNIT,1),//tomamos el gramo como base
                        new Measure("mg",ctx.getString(R.string.milligram),IS_UNIT,0.001),
                        new Measure("ug",ctx.getString(R.string.microgram),IS_UNIT,0.000001),
                        //sistema de medidas anglosajón tomaremos los usados en EEUU
                        new Measure("oz",ctx.getString(R.string.ounce),ANGLO_UNIT,28.34952313),
                        new Measure("lb",ctx.getString(R.string.pound),ANGLO_UNIT,453.592),
                        new Measure("st",ctx.getString(R.string.stone),ANGLO_UNIT,6350.29318),
                        new Measure("at",ctx.getString(R.string.atWeigth),ANGLO_UNIT,11339.81),
                        new Measure("ctw",ctx.getString(R.string.shortHundredweight),ANGLO_UNIT,45359.24),
                        new Measure("qtr",ctx.getString(R.string.shortQuarter),ANGLO_UNIT,226796.185),
                        new Measure("ton",ctx.getString(R.string.shortTon),ANGLO_UNIT,907184.74)
                ));
                break;
            case ELECTRICITY:
                measureList = new ArrayList<>(Arrays.asList(
                        new Measure("cb",ctx.getString(R.string.coulomb),0,1),
                        new Measure("cb",ctx.getString(R.string.abccoulomb),0,10),
                        new Measure("Ahr",ctx.getString(R.string.ampereHour),0,3600),
                        new Measure("F",ctx.getString(R.string.faraday),0,96521.8999999997),
                        new Measure("cb",ctx.getString(R.string.statCoulomb),0,0.000000000333564),
                        new Measure("mF",ctx.getString(R.string.milliFaraday),0,96.5219),
                        new Measure("muF",ctx.getString(R.string.microFaraday),0,9.65219e-2),
                        new Measure("pF",ctx.getString(R.string.picoFaraday),0,9.65219e-5)
                ));
                break;
            case SPEED:
                measureList = new ArrayList<>(Arrays.asList(
                   new Measure("m/s",ctx.getString(R.string.meterForSecond),0,1),//base metros por segundo
                   new Measure("ft/min",ctx.getString(R.string.footPerMinute),0,5.08e-3),
                   new Measure("ft/s",ctx.getString(R.string.footPerSecond),0,0.3048),
                   new Measure("k/h",ctx.getString(R.string.kilometerPerHour),0,0.2777778),
                   new Measure("milla/h",ctx.getString(R.string.milePerHour),0,0.44704),
                   new Measure("milla/h",ctx.getString(R.string.nauticMilePerHour),0,0.514444),
                   new Measure("Milla/m",ctx.getString(R.string.milePerMinute),0,26.8224),
                   new Measure("milla/s",ctx.getString(R.string.milePerSecond),0,1609.344),
                   new Measure("speedLight",ctx.getString(R.string.lightSpeedDistance),0,2.998e+8),
                   new Measure("Mach",ctx.getString(R.string.speedSound),0,343),
                   new Measure("nudo",ctx.getString(R.string.knot),0,0.514444)

                ));
                break;
            case AREA:
                measureList = new ArrayList<>(Arrays.asList(
                   new Measure("m2",ctx.getString(R.string.squareMeter),0,1),//base el metro cuadrado
                   new Measure("ac",ctx.getString(R.string.acre),0,4046.8564224),
                   new Measure("barn",ctx.getString(R.string.barn),0,1e-28),
                   new Measure("ha",ctx.getString(R.string.hectare),0,10000),
                   new Measure("rood",ctx.getString(R.string.rood),0,1011.71413184285),
                   new Measure("cm2",ctx.getString(R.string.squareCentimeter),0,0.0001),
                   new Measure("km2",ctx.getString(R.string.squareKilometer),0,1000000),
                   new Measure("ft2",ctx.getString(R.string.squareFoot),0,9.290304e-2),
                   new Measure("in2",ctx.getString(R.string.squareInch),0,6.4516e-4),
                   new Measure("mi2",ctx.getString(R.string.squareMile),0,2589988.110336),
                   new Measure("yd2",ctx.getString(R.string.squareYard),0,0.83612736)

                ));
                break;
            case PRESSURE:
                measureList = new ArrayList<>(Arrays.asList(
                        new Measure("pa",ctx.getString(R.string.pascal),0,1),//base
                        new Measure("atm",ctx.getString(R.string.standardAtmosphere),0,101325),
                        new Measure("at",ctx.getString(R.string.technicalAtmosphere),0,98066.5),
                        new Measure("bar",ctx.getString(R.string.bar),0,100000),
                        new Measure("baria",ctx.getString(R.string.barye),0,0.1),
                        new Measure("cmHg",ctx.getString(R.string.centimeterOfMercury),0,1333.22),
                        new Measure("cmH2O",ctx.getString(R.string.centimeterOfWater),0,98.0638),
                        new Measure("dbar",ctx.getString(R.string.decibar),0,1000),
                        new Measure("ftHg",ctx.getString(R.string.mercuryFoot),0,40636.66),
                        new Measure("ftH2O",ctx.getString(R.string.waterFoot),0,2988.98),
                        new Measure("inHg",ctx.getString(R.string.mercuryInch),0,3386.389),
                        new Measure("inH2O",ctx.getString(R.string.waterInch),0,248.082),
                        new Measure("",ctx.getString(R.string.kilogramsMm2),0,9806650),
                        new Measure("kip/in2",ctx.getString(R.string.kilogramsSquareInch),0,6894757),
                        new Measure("mbar",ctx.getString(R.string.millibar),0,100),
                        new Measure("mmHg",ctx.getString(R.string.mercuryMillimeter),0,133.3224),
                        new Measure("mmH2O",ctx.getString(R.string.waterMillimeter),0,9.80638 ),
                        new Measure("hpa",ctx.getString(R.string.hectoPascal),0,100),
                        new Measure("kpa",ctx.getString(R.string.kiloPascal),0,1000),
                        new Measure("lb/ft2",ctx.getString(R.string.poundSquareFoot),0,47.88026),
                        new Measure("psi",ctx.getString(R.string.poundSquareInch),0,6894.757),
                        new Measure("pdf/ft2",ctx.getString(R.string.poundsSquareFoot),0,1.488164),
                        new Measure("sh tn/ft2",ctx.getString(R.string.shortTonSquareFoot),0,95760.518)

                ));
                break;
            case ENERGY:
                measureList = new ArrayList<>(Arrays.asList(
                        new Measure("J",ctx.getString(R.string.joule),0,1),//base
                        new Measure("BTU",ctx.getString(R.string.btuIT),0,1055.056),
                        new Measure("BTU",ctx.getString(R.string.btuTH),0,1054.35),
                        new Measure("BTU",ctx.getString(R.string.btuMean),0,1055.87),
                        new Measure("BTU",ctx.getString(R.string.btu39),0,1059.67),
                        new Measure("BTU",ctx.getString(R.string.btu59),0,1055.87),
                        new Measure("BTU",ctx.getString(R.string.btu60),0,1054.68),
                        new Measure("c",ctx.getString(R.string.calorieThermo),0,4.184),
                        new Measure("c",ctx.getString(R.string.calorieMean),0,4.19002),
                        new Measure("c",ctx.getString(R.string.calorie15),0,4.1858),
                        new Measure("c",ctx.getString(R.string.calorie20),0,4.1819),
                        new Measure("eV",ctx.getString(R.string.electronVolt),0,1.6021e-19 ),
                        new Measure("erg",ctx.getString(R.string.erg),0,0.0000001),
                        new Measure("ft-pdl",ctx.getString(R.string.poundsFoot),0,4.214011e-2),
                        new Measure("ft-lbf",ctx.getString(R.string.footPoundForce),0,1.355818),
                        new Measure("",ctx.getString(R.string.horsePowerHour),0,2684077.3),
                        new Measure("in-lbf",ctx.getString(R.string.forceInchPound),0,0.112984829),
                        new Measure("IT",ctx.getString(R.string.kiloCalorieIT),0,4186.8),
                        new Measure("mean",ctx.getString(R.string.kiloCalorieMean),0,4190.02),
                        new Measure("kw-Hr",ctx.getString(R.string.kilowattHour),0,3600000),
                        new Measure("",ctx.getString(R.string.quadIT),0,1.05506E+18),
                        new Measure("",ctx.getString(R.string.thermU_S),0,105480400),
                        new Measure("toc",ctx.getString(R.string.coalTon),0,293076E+5),
                        new Measure("toe",ctx.getString(R.string.oilTon),0,41868E+6),
                        new Measure("tTNT",ctx.getString(R.string.tntTon),0,4184E+6),
                        new Measure("Wh",ctx.getString(R.string.wattHour),0,3600),
                        new Measure("Ws",ctx.getString(R.string.wattSecond),0,1)
                ));
                break;
            case VOLUME:
                measureList = new ArrayList<>(Arrays.asList(
                        new Measure("m3",ctx.getString(R.string.cubicMeter),0,1),//base
                        new Measure("",ctx.getString(R.string.cubicCentimeter),0,0.000001),
                        new Measure("",ctx.getString(R.string.cubicMillimeter),0,0.000000001),
                        new Measure("",ctx.getString(R.string.acreFoot),0,1233.481838),
                        new Measure("",ctx.getString(R.string.petroleumBarrel),0,0.1589873),
                        new Measure("",ctx.getString(R.string.cup),0,0.0002365882),
                        new Measure("",ctx.getString(R.string.fireWoodCord),0,3.624556364),
                        new Measure("",ctx.getString(R.string.cubicFoot),0,0.028316847),
                        new Measure("",ctx.getString(R.string.cubicInch),0,1.63871E-05),
                        new Measure("",ctx.getString(R.string.fluidOunce),0,2.95735E-05),
                        new Measure("",ctx.getString(R.string.ounceImperialMeasure),0,2.84131E-05),
                        new Measure("",ctx.getString(R.string.gallonUK),0,0.004546087),
                        new Measure("",ctx.getString(R.string.gallonUS),0,0.004404884),
                        new Measure("",ctx.getString(R.string.cubicInch2),0,0.00001638706),
                        new Measure("",ctx.getString(R.string.liter),0,0.001),
                        new Measure("",ctx.getString(R.string.tonVolume),0,2.831685),
                        new Measure("",ctx.getString(R.string.cubicYard),0,0.764554858),
                        new Measure("",ctx.getString(R.string.pintUSDry),0,0.0005506105),
                        new Measure("",ctx.getString(R.string.pintUSFluid),0,4.731765e-4),
                        new Measure("",ctx.getString(R.string.quarterUSFluid),0,9.46353e-4),
                        new Measure("",ctx.getString(R.string.quarterImperialMeasure),0,0.001136523)

                ));
                break;

        }
        return measureList;
    }
}
