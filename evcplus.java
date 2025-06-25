import java.util.ArrayList;
import java.util.Scanner;

public class evcplus {
    private static double balance = 120.45;
    private static double bankBalance = 500.00;
    private static String correctPin = "1514";
    private static boolean isLocked = false;
    private static ArrayList<String> history = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean xawaladaActive = true; // Variable for TAAJ

    public static void main(String[] args) {

        welcome();
        if (!authenticate()) return;

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getUserChoice(0, 9);
            if (isLocked && choice != 7 && choice != 0) {
                System.out.println("Xisaabtaada waa la xiray. Fadlan tag Maareynta (7) si aad u furto.");
                continue;
            }
            switch (choice) {
                case 1:
                    displayBalance();
                    break;
                case 2:
                    handleAirtime();
                    break;
                case 3:
                    handleBillPayment();
                    break;
                case 4:
                    handleMoneyTransfer();
                    break;
                case 5:
                    displayHistory();
                    break;
                case 6:
                    handleBankServices();
                    break;
                case 7:
                    handleManagement();
                    break;
                case 8:
                    handleTaaj();
                    break;
                case 9:
                    handleBillPaymentOptions();
                    break;
                case 0:
                    running = false;
                    System.out.println("Waad ka baxday EVC Plus. Mahadsanid!");
                    break;
                default:
                    System.out.println("Doorasho khaldan. Fadlan isku day mar kale.");
            }
        }
        scanner.close();
    }

    private static void welcome() {
        System.out.print("ku soo dhawoow evc (*770#): ");
        String code = scanner.nextLine();
        if (!code.equals("*770#")) {
            System.out.println("Furaha aad gelisay waa khalad.");
            System.exit(0);
        }
    }

    private static boolean authenticate() {
        System.out.print("geli PIN-kaaga: ");
        String pin = scanner.nextLine();
        if (!pin.equals(correctPin)) {
            System.out.println("PIN-ka waa khaldan.");
            return false;
        }
        return true;
    }

    private static void displayMainMenu() {
        System.out.println("------ EVC PLUS ------");
        System.out.println("1. Haraga");
        System.out.println("2. Kaarka Hadalka");
        System.out.println("3. Bixi Biil");
        System.out.println("4. U Wareeji Lacag");
        System.out.println("5. Warbixin");
        System.out.println("6. Salaam Bank");
        System.out.println("7. Maareynta");
        System.out.println("8. TAAJ");
        System.out.println("9. Bill Payment");
        System.out.println("0. Ka Bax");
        System.out.print("Dooro adeegga (0-9): ");
    }

    private static int getUserChoice(int min, int max) {
        int choice;
        while (true) {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= min && choice <= max) break;
            System.out.print("Doorasho khaldan. Fadlan isku day mar kale: ");
        }
        return choice;
    }

    private static void displayBalance() {
        System.out.printf("Haragaaga waa: $%.2f\n", balance);
    }

    private static void handleAirtime() {
        System.out.println("------ KAARKA HADALKA ------");
        System.out.println("1. Ku shubo Airtime");
        System.out.println("2. Ugu shub Airtime qof kale");
        System.out.println("3. MIFI Packages");
        System.out.println("4. Ku shubo Internet");
        System.out.println("5. Ugu shub Internet qof kale");
        System.out.print("Dooro (1-5): ");
        int airtimeChoice = getUserChoice(1, 5);
        switch (airtimeChoice) {
            case 1:
                processAirtimeDeposit();
                break;
            case 2:
                processAirtimeTransfer();
                break;
            case 3:
                processMifiPackages();
                break;
            case 4:
                processInternetDeposit();
                break;
            case 5:
                processInternetTransfer();
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
    }

    private static void processAirtimeDeposit() {
        System.out.print("Geli qadarka Airtime: $");
        double airtime = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= airtime) {
            balance -= airtime;
            history.add("Ku shub Airtime: $" + airtime);
            System.out.println("Airtime waa lagu shubay.");
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void processAirtimeTransfer() {
        System.out.print("Geli number-ka qofka aad ugu shubeyso: ");
        String targetUser = scanner.nextLine();
        System.out.print("Geli qadarka: $");
        double airtimeOther = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= airtimeOther) {
            balance -= airtimeOther;
            history.add("Ugu shub Airtime " + targetUser + ": $" + airtimeOther);
            System.out.println("Airtime waa loo diray " + targetUser + ".");
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void processMifiPackages() {
        String[] mifiPackages = {"10GB - $5", "25GB - $10", "50GB - $18"};
        for (int i = 0; i < mifiPackages.length; i++) {
            System.out.println((i + 1) + ". " + mifiPackages[i]);
        }
        System.out.print("Dooro package (1-3): ");
        int mifiChoice = getUserChoice(1, 3);
        double mifiCost = 0;
        switch (mifiChoice) {
            case 1:
                mifiCost = 5;
                break;
            case 2:
                mifiCost = 10;
                break;
            case 3:
                mifiCost = 18;
                break;
        }
        if (balance >= mifiCost) {
            balance -= mifiCost;
            history.add("Ku shubay MIFI Package: $" + mifiCost);
            System.out.println("MIFI package waa la shaqeeyay.");
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void processInternetDeposit() {
        System.out.print("Geli qadarka internet: $");
        double internet = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= internet) {
            balance -= internet;
            history.add("Ku shub Internet: $" + internet);
            System.out.println("Internet waa lagu shubay.");
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void processInternetTransfer() {
        System.out.print("Geli number-ka qofka: ");
        String inetUser = scanner.nextLine();
        System.out.print("Geli qadarka internet: $");
        double inetOther = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= inetOther) {
            balance -= inetOther;
            history.add("Ugu shub internet " + inetUser + ": $" + inetOther);
            System.out.println("Internet waa loo shubay " + inetUser + ".");
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void handleBillPayment() {
        System.out.println("Bixi Biil:");
        System.out.println("1. Post Paid");
        System.out.println("2. Ku Iibso");
        System.out.print("Fadlan dooro (1-2): ");
        int billOption = getUserChoice(1, 2);
        switch (billOption) {
            case 1:
                processPostPaidBill();
                break;
            case 2:
                processProductPurchase();
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
    }

    private static void processPostPaidBill() {
        System.out.print("Geli magaca adeegga Post Paid (e.g. Hormuud): ");
        String postPaidName = scanner.nextLine();
        System.out.print("Geli qadarka: $");
        double postPaidAmount = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= postPaidAmount) {
            balance -= postPaidAmount;
            history.add("Bixiyay Post Paid biil: " + postPaidName + " ($" + postPaidAmount + ")");
            System.out.println("Post Paid biilka " + postPaidName + " oo ah $" + postPaidAmount + " waa la bixiyay.");
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void processProductPurchase() {
        System.out.print("Geli magaca alaabta/adeegga aad iibsanayso: ");
        String product = scanner.nextLine();
        System.out.print("Geli qadarka: $");
        double productAmount = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= productAmount) {
            balance -= productAmount;
            history.add("Iibsaday: " + product + " ($" + productAmount + ")");
            System.out.println("Waxaad ku iibsatay " + product + " oo ah $" + productAmount);
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void handleMoneyTransfer() {
        System.out.print("Geli magaca ama number-ka loo wareejinayo: ");
        String recipient = scanner.nextLine();
        System.out.print("Geli qadarka: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= amount) {
            balance -= amount;
            history.add("Lacag loo wareejiyay " + recipient + ": $" + amount);
            System.out.println("Lacagtii waa la wareejiyay.");
        } else {
            System.out.println("Haraag kuma filna.");
        }
    }

    private static void displayHistory() {
        System.out.println("last action:");
        for (String h : history) {
            System.out.println("- " + h);
        }
    }

    private static void handleBankServices() {
        System.out.println("------ SALAAM BANK ------");
        System.out.println("1. I Tus Haraaga");
        System.out.println("2. Lacag Dhigasho");
        System.out.println("3. Lacag Qaadasho");
        System.out.println("4. Ka wareeji EVC Plus");
        System.out.println("5. Ka wareeji Account-ka");
        System.out.println("6. Hubi Wareejinta Account");
        System.out.println("7. Maareynta Bank");
        System.out.println("8. Kala bax Lacag");
        System.out.print("Dooro (1-8): ");
        int bankOption = getUserChoice(1, 8);
        switch (bankOption) {
            case 1:
                displayBankBalance();
                break;
            case 2:
                depositToBank();
                break;
            case 3:
                withdrawFromBank();
                break;
            case 4:
                transferFromEvcToBank();
                break;
            case 5:
                transferBetweenAccounts();
                break;
            case 6:
                checkLastTransfer();
                break;
            case 7:
                manageBank();
                break;
            case 8:
                withdrawCash();
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
    }

    private static void displayBankBalance() {
        System.out.printf("Haraaga Salaam Bank: $%.2f\n", bankBalance);
    }

    private static void depositToBank() {
        System.out.print("Geli qadarka aad dhigeyso: $");
        double deposit = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= deposit) {
            balance -= deposit;
            bankBalance += deposit;
            history.add("Lacag dhigasho: $" + deposit);
            System.out.println("Waad ku guuleysatay dhigashada.");
        } else {
            System.out.println("Haraag EVC oo ku filan ma jiro.");
        }
    }

    private static void withdrawFromBank() {
        System.out.print("Geli qadarka aad kala bixi rabto: $");
        double withdraw = scanner.nextDouble();
        scanner.nextLine();
        if (bankBalance >= withdraw) {
            bankBalance -= withdraw;
            balance += withdraw;
            history.add("Lacag qaadasho: $" + withdraw);
            System.out.println("Waad ku guuleysatay kala bixidda.");
        } else {
            System.out.println("Lacagta kama filna account-ka.");
        }
    }

    private static void transferFromEvcToBank() {
        System.out.print("Geli qadarka laga wareejinayo EVC Plus: $");
        double fromEvc = scanner.nextDouble();
        scanner.nextLine();
        if (balance >= fromEvc) {
            balance -= fromEvc;
            bankBalance += fromEvc;
            history.add("Wareejin EVC -> Bank: $" + fromEvc);
            System.out.println("Lacagta waa la wareejiyay.");
        } else {
            System.out.println("EVC-ga kuma filna.");
        }
    }

    private static void transferBetweenAccounts() {
        System.out.print("Geli account-ka la wareejinayo: ");
        String targetAcc = scanner.nextLine();
        System.out.print("Geli qadarka: $");
        double sendAcc = scanner.nextDouble();
        scanner.nextLine();
        if (bankBalance >= sendAcc) {
            bankBalance -= sendAcc;
            history.add("Lacag loo wareejiyay " + targetAcc + ": $" + sendAcc);
            System.out.println("Lacagta waa la wareejiyay.");
        } else {
            System.out.println("Lacag kama filna account-ka.");
        }
    }

    private static void checkLastTransfer() {
        System.out.println("Wareejinadii u dambeysay:");
        boolean found = false;
        for (int i = history.size() - 1; i >= 0; i--) {
            if (history.get(i).toLowerCase().contains("wareejiyay")) {
                System.out.println("- " + history.get(i));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ma jiraan wareejinno hore.");
        }
    }

    private static void manageBank() {
        System.out.println("Maareynta Bank:");
        System.out.println("1. Badel PIN");
        System.out.println("2. Warbixin Bank");
        System.out.print("Dooro (1-2): ");
        int mng = getUserChoice(1, 2);
        if (mng == 1) {
            changePin();
        } else if (mng == 2) {
            displayBankHistory();
        }
    }

    private static void changePin() {
        System.out.print("Geli PIN hore: ");
        String oldPin = scanner.nextLine();
        if (oldPin.equals(correctPin)) {
            System.out.print("Geli PIN cusub: ");
            correctPin = scanner.nextLine();
            System.out.println("PIN waa la beddelay.");
        } else {
            System.out.println("PIN hore waa khaldan.");
        }
    }

    private static void displayBankHistory() {
        System.out.println("Dhaqdhaqaaqa Salaam Bank:");
        for (String h : history) {
            if (h.contains("Bank")) {
                System.out.println("- " + h);
            }
        }
    }

    private static void withdrawCash() {
        System.out.print("Geli qadarka kala baxa: $");
        double outCash = scanner.nextDouble();
        scanner.nextLine();
        if (bankBalance >= outCash) {
            bankBalance -= outCash;
            balance += outCash;
            history.add("Kala baxay: $" + outCash);
            System.out.println("Kala bixiddu waa guuleysatay.");
        } else {
            System.out.println("Ma heysid lacag ku filan.");
        }
    }

    private static void handleManagement() {
        System.out.println("------ MAAREYNTA ------");
        System.out.println("1. Badal PIN");
        System.out.println("2. Badal Luqada");
        System.out.println("3. Wargelin Mobile Lumay");
        System.out.println("4. Lacag Xirasho");
        System.out.println("5. U Celi Lacag Qaldantay");
        System.out.print("Dooro (1-5): ");
        int manageChoice = getUserChoice(1, 5);
        switch (manageChoice) {
            case 1:
                changePin();
                break;
            case 2:
                changeLanguage();
                break;
            case 3:
                reportLostMobile();
                break;
            case 4:
                lockAccount();
                break;
            case 5:
                refundWrongTransaction();
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
    }

    private static void changeLanguage() {
        System.out.println("Dooro luqada:");
        System.out.println("1. English");
        System.out.println("2. Somali");
        int lang = getUserChoice(1, 2);
        if (lang == 1) {
            System.out.println("Language changed to English.");
        } else {
            System.out.println("Luuqadda waa Somali.");
        }
    }

    private static void reportLostMobile() {
        System.out.println("Wargelin: Mobile-ka waa lumay. Xogtaada waa la xannibay si ku-meel-gaar ah.");
    }

    private static void lockAccount() {
        isLocked = true;
        System.out.println("Xisaabtaada waa la xiray. Waxaad u baahan tahay inaad la xiriirto adeegga macaamiisha si aad u furto.");
    }

    private static void refundWrongTransaction() {
        System.out.print("Magaca ama Number-ka lacagta si qalad ah ugu dhacday: ");
        String wrongRecipient = scanner.nextLine();
        System.out.print("Qadarka lacagta: $");
        double refundAmount = scanner.nextDouble();
        scanner.nextLine();
        balance += refundAmount;
        history.add("Lacag laga celiyay " + wrongRecipient + ": $" + refundAmount);
        System.out.println("Lacagta waa la celiyay.");
    }


    private static double totalBill = 45.75;

    private static void handleTaaj() {
        String[] taajOptions = {
                "Heli Adeega Gudaha",
                "Heli Adeega Dibada",
                "Ogaaw Khidmada",
                "Macluumaadka Xawalada",
                "Jooji Xawalada",
                "Furo Xawalada"
        };

        System.out.println("------ TAAJ ------");
        for (int i = 0; i < taajOptions.length; i++) {
            System.out.println((i + 1) + ". " + taajOptions[i]);
        }
        System.out.print("Dooro (1-6): ");
        int taajChoice = getUserChoice(1, 6);

        switch (taajChoice) {
            case 1:
                System.out.println(xawaladaActive ? "Waxaad heshay adeega gudaha." : "Adeega gudaha waa la joojiyay.");
                break;
            case 2:
                System.out.println(xawaladaActive ? "Waxaad heshay adeega dibada." : "Adeega dibada waa la joojiyay.");
                break;
            case 3:
                System.out.println("Khidmada waa 2% lacagta la xawilo.");
                break;
            case 4:
                System.out.println("Macluumaadka xawalada: Xawiladu waxay qaadataa 1-2 saacadood.");
                break;
            case 5:
                xawaladaActive = false;
                System.out.println("Xawalada waa la joojiyay.");
                break;
            case 6:
                xawaladaActive = true;
                System.out.println("Xawalada waa la furay.");
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
    }

    private static void handleBillPaymentOptions() {
        System.out.println("------ BILL PAYMENT ------");
        System.out.println("1. I tusi haraaga biilka");
        System.out.println("2. Bixi biilka oo dhan");
        System.out.println("3. Qeyb ka bixi biilka");
        System.out.print("Dooro (1-3): ");
        int billOption = getUserChoice(1, 3);

        switch (billOption) {
            case 1:
                System.out.printf("Haraaga biilkaaga waa: $%.2f\n", totalBill);
                break;
            case 2:
                payFullBill(totalBill);
                totalBill = 0; // biilka waa la bixiyay oo haraaga 0
                break;
            case 3:
                payPartialBill();
                break;
            default:
                System.out.println("Doorasho aan sax ahayn.");
        }
    }

    private static void payFullBill(double billAmount) {
        if (balance >= billAmount) {
            balance -= billAmount;
            history.add("Biilka oo dhan la bixiyay: $" + billAmount);
            System.out.println("Biilka oo dhan waad bixisay.");
        } else {
            System.out.println("Haraag kuma filna si aad u bixiso biilka oo dhan.");
        }
    }

    private static void payPartialBill() {
        System.out.printf("Haraagaaga hadda waa: $%.2f\n", totalBill);
        System.out.print("Geli qadarka aad rabto inaad bixiso: $");
        double partial = scanner.nextDouble();
        scanner.nextLine();

        if (partial > totalBill) {
            System.out.println("Qadarka aad gelisay wuu ka badan yahay biilka harsan.");
        } else if (balance >= partial) {
            balance -= partial;
            totalBill -= partial;
            history.add("Qeyb biil la bixiyay: $" + partial);
            System.out.printf("Waxaad ka bixisay biilka: $%.2f. Haraaga harsan: $%.2f\n", partial, totalBill);
        } else {
            System.out.println("Haraag kuma filna si aad u bixiso qadarkaasi.");
        }
    }
}