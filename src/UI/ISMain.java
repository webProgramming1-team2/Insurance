package UI;

import compensation.Compensation;
import compensation.CompensationList;
import compensation.CompensationListImpl;
import contract.Contract;
import contract.ContractListImpl;
import customer.Customer;
import customer.CustomerListImpl;
import demand.Demand;
import demand.DemandListImpl;
import insurance.Insurance;
import insurance.InsuranceListImpl;
import sales.Sale;
import sales.SalesListImpl;
import utils.*;
import uw.LossRate;
import uw.UW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.sasl.AuthorizeCallback;

public class ISMain {
    CustomerListImpl customerListImpl;
    ArrayList<Customer> customerList;
    ContractListImpl contractListImpl;
    ArrayList<Contract> contractList;
    SalesListImpl salesListImpl;
    ArrayList<Sale> saleList;
    CompensationListImpl compensationListImpl;
    ArrayList<Compensation> compensationList;
    DemandListImpl demandListImpl;
    ArrayList<Demand> demandList;
    InsuranceListImpl insuranceList;
    public ISMain() {
        insuranceList = new InsuranceListImpl();
        insuranceList.add(new Insurance(1, "dddd", 2, "AAA", 111, "보험1"));

        customerListImpl = new CustomerListImpl();
        customerListImpl.add(new Customer("zxcvbn","김범준","용인",24,"남자","무직"));
        customerList = customerListImpl.retrieve();

        contractListImpl = new ContractListImpl();
        contractListImpl.add(new Contract(12551,10000000,new Date(124,01,11),"자세한 내용은 약관을 참조하세요1"));
        contractListImpl.add(new Contract(12551,10000000,new Date(121,01,11),"자세한 내용은 약관을 참조하세요1"));
        contractListImpl.add(new Contract(12551,1000032000,new Date(124,01,02),"자세한 내용은 약관을 참조하세요3"));
        contractList = contractListImpl.retrieve();

        salesListImpl = new SalesListImpl();
        salesListImpl.add(new Sale("wertyy","qwerty",12551, new Date()));
        salesListImpl.add(new Sale("zxcvbn","asdfgh",12121, new Date()));
        saleList = salesListImpl.retrieve();

        compensationListImpl = new CompensationListImpl();
        compensationListImpl.add(new Compensation("2401", "343000", 8480000, 7));
        compensationListImpl.add(new Compensation("2408", "211000", 4450000, 9));
        compensationListImpl.add(new Compensation("2580", "747000", 12800000, 3));
        compensationList = compensationListImpl.retrieve();

        demandListImpl = new DemandListImpl();
        demandListImpl.add(new Demand("6656", 20050111, "교통사고", 0, "사고내용 1", "복합골절", 1, "서울대학교병원", "지영호", "4568542381658", "외국은행", "본인"));
    }

    public static void main(String[] args) throws NotBoundException, IOException {
        BufferedReader objectReader = new BufferedReader(new InputStreamReader(System.in));
        ISMain isMain = new ISMain();
        try {
            while (true) {
                isMain.printMenu();
                String sChoice = objectReader.readLine().trim();
                switch (sChoice) {
                    case "1":
                        isMain.printContractMenu(objectReader);
                        break;
                    case "2":
                        isMain.printCompensationMenu(objectReader);
                        break;
                    case "3":
                        isMain.printMarketingMenu(objectReader);
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invaild choice !!!");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printCompensationMenu(BufferedReader objectReader) {
        try {
            while (true) {
                System.out.println("1. 보상관리");
                System.out.println("2. 보상평가");
                System.out.println("3. 손해조사");
                System.out.println("4. 보상심사");
                System.out.println("x. 나가기");
                String userInput = objectReader.readLine().trim();
                switch (userInput) {
                    case "1":
                        manageCompensation(objectReader);
                        break;
                    case "2":
                        examineCompensation(objectReader);
                        break;
                    case "3":
                        investigateDamage(objectReader);
                        break;
                    case "4":
                        evaluateCompensation(objectReader);
                    case "x":
                        return;
                    default:
                        System.out.println("Invaild choice !!!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printMarketingMenu(BufferedReader objectReader) {
        try {
            while (true) {
                System.out.println("1. 고객정보관리");
                System.out.println("2. 회원가입");
                System.out.println("3. 영업활동관리");
                System.out.println("x. 나가기");
                String sChoice = objectReader.readLine().trim();
                switch (sChoice) {
                    case "1":
                        manageCustomers(objectReader);
                        break;
                    case "2":
                        registerCustomer(objectReader);
                        break;
                    case "3":
                        manageSale(objectReader);
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invaild choice !!!");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SaveFailedException e) {
            System.out.println(e.getMessage());
        } catch (EmptyValueException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("프로그램 종료");
    }

    private void printContractMenu(BufferedReader objectReader) {
        try {
            while (true) {
                System.out.println("1. 상품개발");
                System.out.println("2. UW");
                System.out.println("3. 계약관리");
                System.out.println("4. 계약통계");
                System.out.println("x. 나가기");
                String sChoice = objectReader.readLine().trim();
                switch (sChoice) {
                    case "1":
                        designInsurance(objectReader);
                        break;
                    case "2":
                        uwStarted(objectReader);
                        break;
                    case "3":
                        manageContracts(objectReader);
                        break;
                    case "4":
                        contractStatics(objectReader);
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("Invaild choice !!!");
                }
            }
        } catch (IOException | InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (SaveFailedException e) {
            System.out.println(e.getMessage());
        } catch (EmptyValueException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (NoFileException e) {
            System.out.println(e.getMessage());
        } catch (NoExpiredContractException e) {
            System.out.println(e.getMessage());
        }
    }

    private void designInsurance(BufferedReader objectReader) throws IOException, InvalidInputException {
        while (true) {
            System.out.println("1. 보장내용 입력");
            System.out.println("2. 요율 계산");
            System.out.println("3. 상품 인가");
            System.out.println("x. 나가기");
            String choiceInsuranceMenu = objectReader.readLine().trim();
            try {
                switch (choiceInsuranceMenu) {
                    case "1":
                        Insurance insurance = createInsurance(objectReader);
                        System.out.println("요율은 <"+insurance.calculateRate()+"> 입니다.");
                        authorizeInsurance(objectReader, insuranceList, insurance);
                        break;
                    case "2":
                        showInsuranceList(insuranceList);
                        Integer choiceNumber = readIntegerInput(objectReader, "상품번호를 입력해주세요");
                        Insurance choiceInsurance = insuranceList.getInsuranceList().get(choiceNumber - 1);
                        System.out.println("요율은 <"+choiceInsurance.calculateRate()+"> 입니다.");
                        break;
                    case "3":
                        showInsuranceList(insuranceList);
                        Integer choiceNumber1 = readIntegerInput(objectReader, "상품번호를 입력해주세요");
                        Insurance choiceInsurance1 = insuranceList.getInsuranceList().get(choiceNumber1 - 1);
                        choiceInsurance1.authorize(objectReader);
                        break;
                    case "x":
                        return;
                    default:
                        throw new InvalidInputException("입력은 1,2,3중 하나 입니다.");
                }
                break;
            } catch (InvalidInputException | EmptyValueException e) {
                System.out.println(e.getMessage());
            } catch (ConnectErrorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private
    void showInsuranceList(InsuranceListImpl insuranceList) {
        int cnt = 1;
        for (Insurance insurance : insuranceList.getInsuranceList()) {
            System.out.println(cnt+" :  "+" Name : "+insurance.getInsuranceName());
            cnt++;
        }
    }

    private void authorizeInsurance(BufferedReader objectReader, InsuranceListImpl insuranceList, Insurance insurance) throws IOException, EmptyValueException {
        while (true) {
            System.out.println("1. 상품 인가");
            System.out.println("2. 상품 임시저장");
            String authorizeChoice =  objectReader.readLine().trim();
            try {
                if(authorizeChoice.equals("1")) {
                    boolean authorized = insurance.authorize(objectReader);
                    if (authorized) {
                        insuranceList.add(insurance);
                        System.out.println("authorized = " + authorized);
                    }
                    break;
                }
                else if(authorizeChoice.equals("2")) {
                    System.out.print("임시 상품명: ");
                    String temporalName = objectReader.readLine().trim();
                    insurance.setInsuranceName(temporalName);
                    insuranceList.add(insurance);
                    break;
                } else {
                    throw new InvalidInputException("입력은 1,2중 하나 입니다.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (ConnectErrorException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Insurance createInsurance(BufferedReader objectReader) throws IOException, InvalidInputException, EmptyValueException {
        while (true) {
            try {
                System.out.print("보장대상: ");
                String coverageTarget = objectReader.readLine().trim();
                System.out.print("보장사건: ");
                String coverageEvent = objectReader.readLine().trim();
                Integer coverageAmount = readIntegerInput(objectReader, "보상금액: ");
                Integer coveragePeriod = readIntegerInput(objectReader, "보장기간: ");
                Integer insuranceFee = readIntegerInput(objectReader, "보험료: ");
                System.out.print("보험명: ");
                String insuranceName = objectReader.readLine().trim();
                validateInsuranceInput(coverageTarget, coverageEvent, coverageAmount, coveragePeriod, insuranceFee, insuranceName);
                return new Insurance(coverageAmount, coverageEvent, coveragePeriod, coverageTarget, insuranceFee, insuranceName);
            } catch (InvalidInputException | EmptyValueException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateInsuranceInput(String coverageTarget, String coverageEvent, Integer coverageAmount, Integer coveragePeriod, Integer insuranceFee, String insuranceName) throws EmptyValueException {
        List<String> missingFields = new ArrayList<>();
        if (coverageAmount <= 0) {
            missingFields.add("보상금액");
        }
        if (coveragePeriod <= 0) {
            missingFields.add("보장기간");
        }
        if (coverageEvent == null || coverageEvent.isEmpty()) {
            missingFields.add("보장사건");
        }
        if (coverageTarget == null || coverageTarget.isEmpty()) {
            missingFields.add("보장대상");
        }
        if (insuranceFee <= 0) {
            missingFields.add("보험료");
        }
        if (insuranceName == null || insuranceName.isEmpty()) {
            missingFields.add("상품명");
        }
        if (!missingFields.isEmpty()) {
            String message = String.format("%s 정보를 입력하지 않았습니다.", String.join(", ", missingFields));
            throw new EmptyValueException(message);
        }
    }

    private
    Integer readIntegerInput(BufferedReader objectReader, String message) throws IOException, InvalidInputException {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(objectReader.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식으로 입력해야합니다. 다시 입력해주세요.");
            }
        }
    }

    /**
     * ---------------------------------------------------------------------------
     */

    private void registerCustomer(BufferedReader objectReader) throws IOException, EmptyValueException, NumberFormatException, SaveFailedException {
        System.out.println("ID를 입력해주세요.");
        String newCustomerID = objectReader.readLine().trim();
        System.out.println("이름을 입력해주세요.");
        String newCustomerName = objectReader.readLine().trim();
        System.out.println("주소를 입력해주세요.");
        String newCustomerAddress = objectReader.readLine().trim();
        System.out.println("직업을 입력해주세요.");
        String newCustomerJob = objectReader.readLine().trim();
        System.out.println("성별을 입력해주세요.");
        String newCustomerGender = objectReader.readLine().trim();
        System.out.println("나이를 입력해주세요.");
        String newCustomerAge = objectReader.readLine().trim();

        //입력한 값이 하나라도 비어있을 경우

        try{
            if(newCustomerID.isEmpty() || newCustomerName.isEmpty()
                    ||newCustomerAddress.isEmpty() || newCustomerJob.isEmpty()
                    ||newCustomerGender.isEmpty() || newCustomerAge.isEmpty()){
                throw new EmptyValueException("모든 내용을 빠짐없이 입력해주세요.");
            }else{
                //제대로 입력이 됐을 경우 리스트에 저장
                customerList.add(new Customer(newCustomerID, newCustomerName,
                        newCustomerAddress,Integer.parseInt(newCustomerAge),
                        newCustomerGender, newCustomerJob));
                System.out.println("회원가입에 성공했습니다.");
            }
        }
        catch (NumberFormatException e){
            throw new SaveFailedException();
        }

    }

    private void contractStatics(BufferedReader objectReader) throws IOException, NoExpiredContractException, NoFileException {
        showContractsList();
        System.out.println("*********************MENU********************");
        System.out.println("1. 만료된 계약 찾기");
        System.out.println("x. 나가기");

        String sChoice = objectReader.readLine().trim();
        switch(sChoice){
            case "1":
                showExpiredContract();
                break;
            case "x":
                return;
            default:
                System.out.println("올바르지 않은 입력입니다.");
                break;
        }
    }

    private  void manageSale(BufferedReader objectReader) throws IOException, EmptyValueException, NumberFormatException, SaveFailedException {

        System.out.println("*********************MENU********************");
        System.out.println("1. 영업활동 내역서 작성");
        System.out.println("x. 나가기");
        String sChoice = objectReader.readLine().trim();

        try{
            switch(sChoice) {
                case "1":
                    System.out.println("고객ID를 입력해주세요.");
                    String customerID = objectReader.readLine().trim();
                    System.out.println("직원ID를 입력해주세요.");
                    String employeeID = objectReader.readLine().trim();
                    System.out.println("보험ID를 입력해주세요.");
                    String insuranceID = objectReader.readLine().trim();

                    if(customerID.isEmpty() || employeeID.isEmpty()|| insuranceID.isEmpty()){
                        throw new EmptyValueException("모든 내용을 빠짐없이 입력해주세요.");
                    }else{
                        saleList.set(Integer.parseInt(sChoice) - 1,
                                new Sale(customerID, employeeID,
                                        Integer.parseInt(insuranceID),new Date()));
                        System.out.println("정보를 저장했습니다.");
                    }
                    break;
                case "x":
                    break;
            }
        }catch (NumberFormatException e){
            throw new SaveFailedException();
        }
    }

    private void showExpiredContract() throws NoExpiredContractException {
        boolean checkExpired = false; // 만료된 계약이 없는지 확인
        int count = 1;

        System.out.println("--------------만료된 계약---------------------");
        for(Contract contract: contractList){
            if(contract.checkExpired()){
                System.out.println(count + ".");
                System.out.println("보험ID: "+contract.getInsuraceID());
                System.out.println("보험료: "+contract.getInsuranceFee());
                System.out.println("보험만료일: "+contract.getExpirationDate());
                System.out.println("보험보장 세부사항: "+contract.getCoverageDetails());
                System.out.println("-------------------------------------------------------------");
                checkExpired = true; // 만료된 계약이 하나라도 있다면 True
                ++count;
            }
        }
        if(!checkExpired){
            throw new NoExpiredContractException(); //만료된 계약이 없으면 예외생성
        }
    }

    private  void manageCustomers(BufferedReader objectReader) throws IOException, EmptyValueException, IndexOutOfBoundsException, NumberFormatException, SaveFailedException {

        showCustomerList();
        String sChoice = objectReader.readLine().trim();
        Customer selectedCustomer = customerList.get(Integer.parseInt(sChoice) - 1);

        editCustomer(objectReader, selectedCustomer);
    }


    private void editCustomer(BufferedReader objectReader, Customer selectedCustomer) throws IOException, EmptyValueException, SaveFailedException {
        System.out.println("ID: "+selectedCustomer.getCustomerID());
        System.out.println("이름: "+selectedCustomer.getCustomerName());
        System.out.println("주소: "+selectedCustomer.getAddress());
        System.out.println("직업: "+selectedCustomer.getJob());
        System.out.println("성별: "+selectedCustomer.getGender());
        System.out.println("나이: "+selectedCustomer.getAge());
        System.out.println("*********************MENU********************");
        System.out.println("1. 고객 정보 수정");
        System.out.println("x. 나가기");
        String sChoice = objectReader.readLine().trim();
        int a;
        try{
            switch(sChoice){
                case "1":
                    System.out.println("수정할 ID를 입력해주세요.");
                    String newCustomerID = objectReader.readLine().trim();
                    System.out.println("수정할 이름 입력해주세요.");
                    String newCustomerName = objectReader.readLine().trim();
                    System.out.println("수정할 주소를 입력해주세요.");
                    String newCustomerAddress = objectReader.readLine().trim();
                    System.out.println("수정할 직업을 입력해주세요.");
                    String newCustomerJob = objectReader.readLine().trim();
                    System.out.println("수정할 성별을 입력해주세요.");
                    String newCustomerGender = objectReader.readLine().trim();
                    System.out.println("수정할 나이를 입력해주세요.");
                    String newCustomerAge = objectReader.readLine().trim();

                    if(newCustomerID.isEmpty() || newCustomerName.isEmpty()
                            ||newCustomerAddress.isEmpty() || newCustomerJob.isEmpty()
                            ||newCustomerGender.isEmpty() || newCustomerAge.isEmpty()){
                        throw new EmptyValueException("모든 내용을 빠짐없이 입력해주세요.");
                    }else{
                        customerList.set(Integer.parseInt(sChoice) - 1,
                                new Customer(newCustomerID, newCustomerName,
                                        newCustomerAddress,Integer.parseInt(newCustomerAge),
                                        newCustomerGender, newCustomerJob));
                        System.out.println("고객정보를 저장했습니다.");
                    }
                    break;
                case "x":
                    return;
                default:
                    System.out.println("올바르지 않은 입력입니다.");
                    break;
            }

        }catch (NumberFormatException e){
            throw new SaveFailedException();
        }



    }
    private void editContract(BufferedReader objectReader, Contract selectedContract) throws IOException, EmptyValueException, ParseException, SaveFailedException {
        System.out.println("보험ID: "+selectedContract.getInsuraceID());
        System.out.println("보험료: "+selectedContract.getInsuranceFee());
        System.out.println("보험만료일: "+selectedContract.getExpirationDate());
        System.out.println("보험보장 세부사항: "+selectedContract.getCoverageDetails());

        System.out.println("*********************MENU********************");
        System.out.println("1. 계약 정보 수정하기");
        System.out.println("x. 나가기");
        String sChoice = objectReader.readLine().trim();
        switch(sChoice){
            case "1":
                System.out.println("수정할 보험ID를 입력해주세요.");
                String newInsuranceID = objectReader.readLine().trim();
                System.out.println("수정할 보험료를 입력해주세요.");
                String newInsuranceFee = objectReader.readLine().trim();
                System.out.println("수정할 보험만료일(yyyyMMdd)을 입력해주세요.");
                String newExpirationDate = objectReader.readLine().trim();
                System.out.println("수정할 보험보장 세부사항을 입력해주세요.");
                String newCoverageDetails = objectReader.readLine().trim();



                try{
                    if(newInsuranceID.isEmpty() || newInsuranceFee.isEmpty()
                            ||newCoverageDetails.isEmpty() || newExpirationDate.isEmpty()){
                        throw new EmptyValueException("모든 내용을 빠짐없이 입력해주세요.");
                    }else{
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                        contractList.set(Integer.parseInt(sChoice) - 1,
                                new Contract(Integer.parseInt(newInsuranceID), Integer.parseInt(newInsuranceFee),
                                        simpleDateFormat.parse(newExpirationDate),newCoverageDetails));
                        System.out.println("계약정보를 저장했습니다.");
                    }
                    break;
                }
                catch (NumberFormatException e){
                    throw new SaveFailedException();
                }
            case "x":
                return;
            default:
                System.out.println("올바르지 않은 입력입니다.");
                break;
        }

    }
    private void manageContracts(BufferedReader objectReader) throws IOException, EmptyValueException, ParseException, IndexOutOfBoundsException, NoFileException, SaveFailedException {
        showContractsList();
        String sChoice = objectReader.readLine().trim();
        Contract selectedContract = contractList.get(Integer.parseInt(sChoice) - 1);
        editContract(objectReader,selectedContract);


    }

    private void showContractsList() throws NoFileException {
        int count = 1;
        System.out.println("-------------------------계약 목록---------------------------");
        try{
            for(Contract contract: contractList){
                System.out.println(count + ".");
                System.out.println("보험ID: "+contract.getInsuraceID());
                System.out.println("보험료: "+contract.getInsuranceFee());
                System.out.println("보험만료일: "+contract.getExpirationDate());
                System.out.println("보험보장 세부사항: "+contract.getCoverageDetails());
                System.out.println("-------------------------------------------------------------");
                ++count;
            }
            System.out.println("계약번호를 입력해주세요");
        }catch (NullPointerException e){
            throw new NoFileException();
        }

    }
    private void showCustomerList() {
        int count = 1;
        System.out.println("-------------------------고객 목록---------------------------");
        for(Customer customer: customerList){
            System.out.println(count + ".");
            System.out.println("ID: "+customer.getCustomerID());
            System.out.println("이름: "+customer.getCustomerName());
            System.out.println("-------------------------------------------------------------");
            ++count;
        }
        System.out.println("고객번호를 입력해주세요.");
    }

    /**
     * ------------------------------------------------------------------------------
     */

    private void uwStarted(BufferedReader objectReader) throws RemoteException, IOException {

        System.out.println("----UW 업무를 선택하세요----");
        System.out.println("1. 인수심사");
        System.out.println("2. 재보험처리");
        System.out.println("3. 손해율관리");
        String sChoice = objectReader.readLine().trim();
        switch (sChoice) {
            case "1": //인수심사
                underWriting(objectReader);
                System.out.println("인수 심사가 완료되었습니다.");
                break;
            case "2"://재보험처리
                calculateReinsuranceFee(objectReader);
                System.out.println("재보험 처리가 완료되었습니다.");
                break;
            case "3"://손해율관리
                calculateLossRate(objectReader);
                System.out.println("손해율 계산이 완료되었습니다.");
                break;
            case "x":
                return;
            default:
                System.out.println("Invaild choice!");
        }

    }


    //인수심사
    private void underWriting(BufferedReader objectReader) {


        UW uw = new UW();
        System.out.println("인수 심사를 진행합니다.");

        System.out.println("--청약서 확인.--");
        uw.setApplicationForm(true);
        System.out.println("--증권 확인.--");
        uw.setSecurity(true);

        uw.underWriting(); //자동심사, return true;
        if (uw.underWriting()) {
            System.out.println("심사가 성공했습니다.");
        }
        else
            System.out.println("심사에 실패했습니다.");


        //인수 여부 확인?

    }

    //재보험 처리
    private void calculateReinsuranceFee(BufferedReader objectReader) {


        UW uw = new UW();

        System.out.println("재보험 처리를 진행합니다.");

        //재보험 처리
        if(uw.Reinsurance()) {
            System.out.println("재보험 승인에 성공했습니다.");
            System.out.println("재보험료 : " + uw.calculateReinsuranceFee());
        }
        else
            System.out.println("재보험 승인에 실패했습니다.");


    }

    //손해율관리
    private void calculateLossRate(BufferedReader objectReader) {

        LossRate lossRate = new LossRate();

        System.out.println("계산을 위한 데이터를 입력해주세요.");
        System.out.println("사고 종류 : ");
        String accidentType = null;
        try {
            accidentType = objectReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lossRate.setAccidentType(accidentType);

        System.out.println("보상 한도 : ");
        int coverageLimit = 0;
        try {
            coverageLimit = Integer.parseInt(objectReader.readLine());
            //objectReader.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lossRate.setCoverageLimit(coverageLimit);

        System.out.println("보험료 : ");
        int insuranceFee = 0;
        try {
            insuranceFee = Integer.parseInt(objectReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lossRate.setInsuranceFee(insuranceFee);

        System.out.println("지급된 보상액 : ");
        int paedAmount = 0;
        try {
            paedAmount = Integer.parseInt(objectReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        lossRate.setPaidAmount(paedAmount);

        //계산..
        lossRate.calculateLossRate();

        System.out.println("계산된 손해액 : " + lossRate.getLossRate());

    }

    /**
     * ---------------------------------------------------------------------------
     * @throws ConnectErrorException
     * @throws IOException
     * @throws EmptyValueException
     */
    private void evaluateCompensation(BufferedReader objectReader) throws ConnectErrorException, IOException, EmptyValueException {
        try {
            showCompensationList();
            System.out.println("===========보상 심사============");
        } catch (ConnectErrorException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n원하는 보상의 번호를 입력하시오: ");
        String compensationNumber = objectReader.readLine().trim();
        Compensation selectedCompensation = compensationList.get(Integer.parseInt(compensationNumber) - 1);
        try {
            System.out.println("\n=============선택된 보상============");
            System.out.println("보상금: " + selectedCompensation.getCompensationMoney());
            System.out.println("손해액: " + selectedCompensation.getDamage());
        } catch (NullPointerException e) {
            throw new EmptyValueException("해당 자동차 보험에 대하여 산정된 보상 내역이 존재하지 않습니다.");
        }
        System.out.println("1. 결제 요청");
        System.out.println("x. 취소");
        String userInput = objectReader.readLine().trim();
        switch (userInput) {
            case "1":
                confirm(objectReader, selectedCompensation);
                break;
            case "x":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    private void confirm(BufferedReader objectReader, Compensation selectedCompensation) throws IOException, ConnectErrorException, EmptyValueException {
        System.out.println("해당 자동차 보험에 대한 심사 결재를 요청드립니다.");
        System.out.println("-------------------------------------------------------------");
        System.out.println("보상 ID: " + selectedCompensation.getCompensationId());
        System.out.println("보상금: " + selectedCompensation.getCompensationMoney());
        System.out.println("손해액: " + selectedCompensation.getDamage());
        System.out.println("1. 심사 승인 후 결재 확인");
        System.out.println("2. 거절");
        System.out.println("x. 취소");
        String userInput = objectReader.readLine().trim();
        switch (userInput) {
            case "1":
            System.out.println("보상 심사가 완료되었습니다. 확인 부탁드립니다.");
                payCompensation(objectReader);
                break;
            case "2":             
                System.out.println("보상 심사가 거절되었습니다.");
            case "x":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    private void payCompensation(BufferedReader objectReader) throws ConnectErrorException, IOException {
        System.out.println("==========보상금 지급===========");
        Demand demand = demandList.get(0);
        System.out.println("고객명" + demand.getCustomerName());
        System.out.println("계좌번호" + demand.getAccountNumber());
        System.out.println("은행명" + demand.getBank());
        System.out.println("예금주 정보" + demand.getInformation());
        System.out.println("1. 보상금 지급");
        System.out.println("x. 취소");
        String userInput = objectReader.readLine().trim();
        if (userInput == "1") {
            System.out.println("청구된 보상금이 입금되었습니다.");
            return;
        }
        if (userInput == "x") {
            System.out.println("모든 변경 사항을 취소합니다.\n확인");
            return;
        }
    }

    private void investigateDamage(BufferedReader objectReader) throws ConnectErrorException, IOException {
        System.out.println("==============손해 조사===============");
        int count = 1;
        System.out.println("-------------접수 목록--------------");
        try {
            for (Demand demand: demandList) {
                System.out.println(count + ". ");
                System.out.println("ID: " + demand.getDemandId());
                System.out.println("고객 이름: " + demand.getCustomerName());
                System.out.println("사고 유형: " + demand.getAccidentType());
                System.out.println("사고 날짜(yyyymmdd): " + demand.getAccidentDate());
                System.out.println("진단명: " + demand.getDiagnosis());
                System.out.println("치료 병원: " + demand.getTreatmentHospital());
                System.out.println("###############################################\n");
                ++count;
            }
        } catch (NullPointerException e) {
            throw new ConnectErrorException("시스템에 장애가 발생하였습니다. 관리자에게 문의하십시오.");
        }
        System.out.println("지급책임이 있습니까?");
        System.out.println("1. 예");
        System.out.println("2. 아니오");
        String userInput = objectReader.readLine().trim();
        if (userInput == "1") {
            System.out.println("지급 금액을 입력하십시오");
            String compensation = objectReader.readLine().trim();
            System.out.println(compensation + "원을 지급합니다.");
            System.out.println("신청하신 사고 내역의 손해 조사 및 보험금 산정이 완료되었습니다.");
            return;
        }
        if (userInput == "2") {
            System.out.println("지급 불가 사유를 입렧하십시오");
            String denial = objectReader.readLine().trim();
            System.out.println(denial + " 사유로 인하여 보험금 지급이 불가합니다.");
            return;
        }
    }

    private void examineCompensation(BufferedReader objectReader) throws ConnectErrorException, IOException {
        System.out.println("==============보상 평가===============");
        showCompensationList();
        System.out.println("\n평가할 보상의 번호를 입력하시오: ");
        String compensationNumber = objectReader.readLine().trim();
        Compensation selectedCompensation = compensationList.get(Integer.parseInt(compensationNumber) - 1);
        System.out.println("\n=============선택된 보상, 변경 전============");
        System.out.println("점수: " + selectedCompensation.getEvaluation());
        System.out.println("점수를 입력하십시오.(0~10)");
        int newEvaluation = Integer.parseInt(objectReader.readLine().trim());
        if (newEvaluation <= 10 && newEvaluation >= 0) {
            compensationList.set(Integer.parseInt(compensationNumber) - 1, 
                                new Compensation(selectedCompensation.getCompensationId(), selectedCompensation.getCompensationMoney(),
                                selectedCompensation.getDamage(), newEvaluation));
                                System.out.println("평가 완료.");
        } else {
            System.out.println("유효하지 않은 입력입니다.");
        }
    }

    private void manageCompensation(BufferedReader objectReader) throws ConnectErrorException, IOException {
        System.out.println("==============보상 관리===============");
        showCompensationList();
        System.out.println("\n==============원하시는 작업을 선택하시오.===============");
        System.out.println("1. 보상 수정");
        System.out.println("2. 보상 폐기");
        System.out.println("x. 나가기");
        String userInput = objectReader.readLine().trim();
        switch (userInput) {
            case "1":
                editCompensation(objectReader);
                System.out.println("수정 완료");
                showCompensationList();
                break;
            case "2":
                deleteCompensation(objectReader);
                System.out.println("폐기 완료");
                showCompensationList();
                break;
            case "x":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }
    private void editCompensation(BufferedReader objectReader) throws IOException, ConnectErrorException {
        System.out.println("\n관리할 보상의 번호를 입력하시오: ");
        String compensationId = objectReader.readLine().trim();
        Compensation selectedCompensation = compensationList.get(Integer.parseInt(compensationId) - 1);
        System.out.println("\n=============선택된 보상, 변경 전============");
        System.out.println("보상금: " + selectedCompensation.getCompensationMoney());
        System.out.println("손해액: " + selectedCompensation.getDamage());
        System.out.println("\n=============선택된 보상, 변경 후============");
        System.out.println("보상금: ");
        String newCompensationMoney = objectReader.readLine().trim();
        System.out.println("손해액: ");
        int newDamage = Integer.parseInt(objectReader.readLine().trim());
        System.out.println("1. 수정");
        System.out.println("2. 취소");
        String userInput = objectReader.readLine().trim();
        if (userInput == "1") {
            compensationList.set(Integer.parseInt(compensationId) - 1, 
                                new Compensation(selectedCompensation.getCompensationId(), newCompensationMoney,
                                newDamage, selectedCompensation.getEvaluation()));
            return;
        }
        if (userInput == "2") {
            System.out.println("모든 변경 사항을 취소합니다.\n확인");
            manageCompensation(objectReader);
            return;
        }
    }
    private void deleteCompensation(BufferedReader objectReader) throws IOException {
        System.out.println("\n폐기할 보상의 번호를 입력하시오: ");
        String compensationId = objectReader.readLine().trim();
        compensationList.remove(Integer.parseInt(compensationId) - 1);
    }
    private void showCompensationList() throws ConnectErrorException {
        int count = 1;
        System.out.println("-------------보상 목록--------------");
        try {
            for (Compensation compensation: compensationList) {
                System.out.println(count + ". ");
                System.out.println("보상 ID: " + compensation.getCompensationId());
                System.out.println("보상금: " + compensation.getCompensationMoney());
                System.out.println("손해액: " + compensation.getCompensationId());
                System.out.println("###############################################\n");
                ++count;
            }
        } catch (NullPointerException e) {
            throw new ConnectErrorException("불러오기에 실패했습니다.\n확인");
        }
    }
    /**
     * -------------------------------------------------------------------------------------
     */


    private void printMenu() {
        System.out.println("*********************MENU********************");
        System.out.println("1. 계약");
        System.out.println("2. 보상");
        System.out.println("3. 마케팅");
        System.out.println("x. 종료하기");
    }
    private
    void showList(ArrayList<?> dataList) throws RemoteException {
        String list = "";
        for(int i=0; i<dataList.size(); i++) {
            list += dataList.get(i) + "\n";
        }
        System.out.println(list);
    }

}
