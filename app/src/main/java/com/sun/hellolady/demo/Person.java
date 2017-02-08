package com.sun.hellolady.demo;

import java.util.List;

/**
 * Created by Jiamin.Sun on 1/25/2016.
 */
public class Person {

    public Person(String n,String a){
        name=n;
        age=a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    private String name;
    private String age;


    /**
     * requestId : d5a30883e2a01e50ff523697af64298c21e71cfbc12608503527fd0994e23bbfba22db1e95a3b6b5efc877e1fc1c63a3468bd588eef2bac3f1c0bf0722886035e53e4f90901a191c326bd938db40a53136c736875edccc64f3287b5a5fcc3ed48fe27f465577cde8d9e947790ad19de46b3a456342fad10ab7941a7959e2f701
     * bidId : null
     * version : 1.0.0
     * action : Q
     * orderInfo : {"traceId":"8d9bc5cce2cd49c796116ff598e7e9d8","generateTime":"151224133244","endTime":null,"orderStatus":"ORDER_OK","orderType":1}
     * tripInfo : null
     * flightSegments : [{"segRef":1,"departureAirportCode":"WUH","arrivalAirportCode":"HKG"}]
     * flightDetails : [{"segRef":1,"flightRef":1,"marketingCarrier":"CX","mcflightNumber":"CX5853","cabin":0,"isShare":0,"flightDeparturedate":"160103","flightArrivaldate":"160103","operatingCarrier":"CX","ocflightNumber":"CX5853","flightDeparturetime":"113500","flightArrivaltime":"133500","departureAirportCode":"WUH","arrivalAirportCode":"HKG"}]
     * passengerDetails : [{"passengerTypecode":"ADT","fullName":"LI/MINGZE","surName":null,"middleName":null,"firstName":null,"gender":"M","age":0,"nationality":"CN","title":null}]
     * pnrInfo : null
     * ticketChangeInfo : {"cancelRestriction":null,"changeRestriction":null,"endorseInformation":"0","baggageAllowance":"根据航空公司规定执行","tgqText":"不支持改期、签转、退票、退税;误机不允许退票、改期;不支持儿童、婴儿、留学生购票","other":null}
     * ext : null
     */

    private String requestId;
    private Object bidId;
    private String version;
    private String action;
    /**
     * traceId : 8d9bc5cce2cd49c796116ff598e7e9d8
     * generateTime : 151224133244
     * endTime : null
     * orderStatus : ORDER_OK
     * orderType : 1
     */

    private OrderInfoEntity orderInfo;
    private Object tripInfo;
    private Object pnrInfo;
    /**
     * cancelRestriction : null
     * changeRestriction : null
     * endorseInformation : 0
     * baggageAllowance : 根据航空公司规定执行
     * tgqText : 不支持改期、签转、退票、退税;误机不允许退票、改期;不支持儿童、婴儿、留学生购票
     * other : null
     */

    private TicketChangeInfoEntity ticketChangeInfo;
    private Object ext;
    /**
     * segRef : 1
     * departureAirportCode : WUH
     * arrivalAirportCode : HKG
     */

    private List<FlightSegmentsEntity> flightSegments;
    /**
     * segRef : 1
     * flightRef : 1
     * marketingCarrier : CX
     * mcflightNumber : CX5853
     * cabin : 0
     * isShare : 0
     * flightDeparturedate : 160103
     * flightArrivaldate : 160103
     * operatingCarrier : CX
     * ocflightNumber : CX5853
     * flightDeparturetime : 113500
     * flightArrivaltime : 133500
     * departureAirportCode : WUH
     * arrivalAirportCode : HKG
     */

    private List<FlightDetailsEntity> flightDetails;
    /**
     * passengerTypecode : ADT
     * fullName : LI/MINGZE
     * surName : null
     * middleName : null
     * firstName : null
     * gender : M
     * age : 0
     * nationality : CN
     * title : null
     */

    private List<PassengerDetailsEntity> passengerDetails;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getBidId() {
        return bidId;
    }

    public void setBidId(Object bidId) {
        this.bidId = bidId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public OrderInfoEntity getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfoEntity orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Object getTripInfo() {
        return tripInfo;
    }

    public void setTripInfo(Object tripInfo) {
        this.tripInfo = tripInfo;
    }

    public Object getPnrInfo() {
        return pnrInfo;
    }

    public void setPnrInfo(Object pnrInfo) {
        this.pnrInfo = pnrInfo;
    }

    public TicketChangeInfoEntity getTicketChangeInfo() {
        return ticketChangeInfo;
    }

    public void setTicketChangeInfo(TicketChangeInfoEntity ticketChangeInfo) {
        this.ticketChangeInfo = ticketChangeInfo;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    public List<FlightSegmentsEntity> getFlightSegments() {
        return flightSegments;
    }

    public void setFlightSegments(List<FlightSegmentsEntity> flightSegments) {
        this.flightSegments = flightSegments;
    }

    public List<FlightDetailsEntity> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(List<FlightDetailsEntity> flightDetails) {
        this.flightDetails = flightDetails;
    }

    public List<PassengerDetailsEntity> getPassengerDetails() {
        return passengerDetails;
    }

    public void setPassengerDetails(List<PassengerDetailsEntity> passengerDetails) {
        this.passengerDetails = passengerDetails;
    }

    public static class OrderInfoEntity {
        private String traceId;
        private String generateTime;
        private Object endTime;
        private String orderStatus;
        private int orderType;

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public String getGenerateTime() {
            return generateTime;
        }

        public void setGenerateTime(String generateTime) {
            this.generateTime = generateTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }
    }

    public static class TicketChangeInfoEntity {
        private Object cancelRestriction;
        private Object changeRestriction;
        private String endorseInformation;
        private String baggageAllowance;
        private String tgqText;
        private Object other;

        public Object getCancelRestriction() {
            return cancelRestriction;
        }

        public void setCancelRestriction(Object cancelRestriction) {
            this.cancelRestriction = cancelRestriction;
        }

        public Object getChangeRestriction() {
            return changeRestriction;
        }

        public void setChangeRestriction(Object changeRestriction) {
            this.changeRestriction = changeRestriction;
        }

        public String getEndorseInformation() {
            return endorseInformation;
        }

        public void setEndorseInformation(String endorseInformation) {
            this.endorseInformation = endorseInformation;
        }

        public String getBaggageAllowance() {
            return baggageAllowance;
        }

        public void setBaggageAllowance(String baggageAllowance) {
            this.baggageAllowance = baggageAllowance;
        }

        public String getTgqText() {
            return tgqText;
        }

        public void setTgqText(String tgqText) {
            this.tgqText = tgqText;
        }

        public Object getOther() {
            return other;
        }

        public void setOther(Object other) {
            this.other = other;
        }
    }

    public static class FlightSegmentsEntity {
        private int segRef;
        private String departureAirportCode;
        private String arrivalAirportCode;

        public int getSegRef() {
            return segRef;
        }

        public void setSegRef(int segRef) {
            this.segRef = segRef;
        }

        public String getDepartureAirportCode() {
            return departureAirportCode;
        }

        public void setDepartureAirportCode(String departureAirportCode) {
            this.departureAirportCode = departureAirportCode;
        }

        public String getArrivalAirportCode() {
            return arrivalAirportCode;
        }

        public void setArrivalAirportCode(String arrivalAirportCode) {
            this.arrivalAirportCode = arrivalAirportCode;
        }
    }

    public static class FlightDetailsEntity {
        private int segRef;
        private int flightRef;
        private String marketingCarrier;
        private String mcflightNumber;
        private int cabin;
        private int isShare;
        private String flightDeparturedate;
        private String flightArrivaldate;
        private String operatingCarrier;
        private String ocflightNumber;
        private String flightDeparturetime;
        private String flightArrivaltime;
        private String departureAirportCode;
        private String arrivalAirportCode;

        public int getSegRef() {
            return segRef;
        }

        public void setSegRef(int segRef) {
            this.segRef = segRef;
        }

        public int getFlightRef() {
            return flightRef;
        }

        public void setFlightRef(int flightRef) {
            this.flightRef = flightRef;
        }

        public String getMarketingCarrier() {
            return marketingCarrier;
        }

        public void setMarketingCarrier(String marketingCarrier) {
            this.marketingCarrier = marketingCarrier;
        }

        public String getMcflightNumber() {
            return mcflightNumber;
        }

        public void setMcflightNumber(String mcflightNumber) {
            this.mcflightNumber = mcflightNumber;
        }

        public int getCabin() {
            return cabin;
        }

        public void setCabin(int cabin) {
            this.cabin = cabin;
        }

        public int getIsShare() {
            return isShare;
        }

        public void setIsShare(int isShare) {
            this.isShare = isShare;
        }

        public String getFlightDeparturedate() {
            return flightDeparturedate;
        }

        public void setFlightDeparturedate(String flightDeparturedate) {
            this.flightDeparturedate = flightDeparturedate;
        }

        public String getFlightArrivaldate() {
            return flightArrivaldate;
        }

        public void setFlightArrivaldate(String flightArrivaldate) {
            this.flightArrivaldate = flightArrivaldate;
        }

        public String getOperatingCarrier() {
            return operatingCarrier;
        }

        public void setOperatingCarrier(String operatingCarrier) {
            this.operatingCarrier = operatingCarrier;
        }

        public String getOcflightNumber() {
            return ocflightNumber;
        }

        public void setOcflightNumber(String ocflightNumber) {
            this.ocflightNumber = ocflightNumber;
        }

        public String getFlightDeparturetime() {
            return flightDeparturetime;
        }

        public void setFlightDeparturetime(String flightDeparturetime) {
            this.flightDeparturetime = flightDeparturetime;
        }

        public String getFlightArrivaltime() {
            return flightArrivaltime;
        }

        public void setFlightArrivaltime(String flightArrivaltime) {
            this.flightArrivaltime = flightArrivaltime;
        }

        public String getDepartureAirportCode() {
            return departureAirportCode;
        }

        public void setDepartureAirportCode(String departureAirportCode) {
            this.departureAirportCode = departureAirportCode;
        }

        public String getArrivalAirportCode() {
            return arrivalAirportCode;
        }

        public void setArrivalAirportCode(String arrivalAirportCode) {
            this.arrivalAirportCode = arrivalAirportCode;
        }
    }

    public static class PassengerDetailsEntity {
        private String passengerTypecode;
        private String fullName;
        private Object surName;
        private Object middleName;
        private Object firstName;
        private String gender;
        private int age;
        private String nationality;
        private Object title;

        public String getPassengerTypecode() {
            return passengerTypecode;
        }

        public void setPassengerTypecode(String passengerTypecode) {
            this.passengerTypecode = passengerTypecode;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Object getSurName() {
            return surName;
        }

        public void setSurName(Object surName) {
            this.surName = surName;
        }

        public Object getMiddleName() {
            return middleName;
        }

        public void setMiddleName(Object middleName) {
            this.middleName = middleName;
        }

        public Object getFirstName() {
            return firstName;
        }

        public void setFirstName(Object firstName) {
            this.firstName = firstName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }
    }
}
