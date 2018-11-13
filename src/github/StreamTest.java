package github;

import test.BankAccountDTO;
import test.Wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: chenyi.zsq
 * @Date: 2018/11/13
 */
public class StreamTest {

    public static void main(String[] args) throws Exception {
        int count = 10;
        int forCount = 1;
        Test1(count,forCount);
        Test2(count,forCount);
        TestSum(count,forCount);
    }

    public static void Test1(int count,int forCount){
        System.out.println("ListAddTest\n");
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            bankAccountDTO.setAccountNo(i + "");
            if (i % 4 == 0) {
                bankAccountDTO.setAccountNo(null);
            }
            bankAccountDTO.setBankName("name");
            bankAccountDTO.setBankCode("code");
            bankAccountDTOList.add(bankAccountDTO);
        }
        long forTimeCount = 0;

        long forEachTimeCount = 0;

        long streamTimeCount = 0;

        long parallelTimeCount = 0;


        for (int i = 0; i < forCount; i++) {
            Long fortimeStart = System.nanoTime();
            List<BankAccountDTO> bankAccountDTOfor = new ArrayList<>();
            for (BankAccountDTO bankAccountDTO : bankAccountDTOList) {
                if (bankAccountDTO.getAccountNo() != null && !bankAccountDTO.getAccountNo().isEmpty()) {
                    bankAccountDTOfor.add(bankAccountDTO);
                }
            }
            Long forTime = (System.nanoTime() - fortimeStart);
            //System.out.println("forTime:" + forTime);
            forTimeCount += forTime;

            Long forEachtimeStart = System.nanoTime();
            List<BankAccountDTO> bankAccountDTOforEach = new ArrayList<>();
            bankAccountDTOList.forEach(bankAccountDTO ->{
                if (bankAccountDTO.getAccountNo() != null && !bankAccountDTO.getAccountNo().isEmpty()) {
                    bankAccountDTOforEach.add(bankAccountDTO);
                }
            });
            Long forEachTime = System.nanoTime() - forEachtimeStart;
            //System.out.println("forEachTime:" + forEachTime);
            forEachTimeCount += forEachTime;

            Long streamTimeStart = System.nanoTime();
            List<BankAccountDTO> bankAccountDTOStream = bankAccountDTOList.stream()
                .filter(account -> account.getAccountNo() != null && !account.getAccountNo().isEmpty())
                .collect(Collectors.toList());
            Long streamTime = (System.nanoTime() - streamTimeStart);
            //System.out.println("streamTime:" + streamTime);
            streamTimeCount += streamTime;

            Long parallelStreamTimeStart = System.nanoTime();
            List<BankAccountDTO> bankAccountDTOparallelStream = bankAccountDTOList.parallelStream()
                .filter(account -> account.getAccountNo() != null && !account.getAccountNo().isEmpty())
                .collect(Collectors.toList());
            Long parallelStream = (System.nanoTime() - parallelStreamTimeStart);
            //System.out.println("parallelStream:" + parallelStream);
            parallelTimeCount += parallelStream;

            //System.out.println("length:" + bankAccountDTOfor.size() + " " + bankAccountDTOStream.size()+ " " + bankAccountDTOparallelStream.size()+"\n");
        }
        System.out.println(
            "CountNanos for:" + forTimeCount + " forLam:" + forEachTimeCount + " stream:" + streamTimeCount + " parrelStream:"
                + parallelTimeCount);
        System.out.println(
            "CountMills for:" + forTimeCount / 1000000 + " forLam:" + forEachTimeCount / 1000000 + " stream:" + streamTimeCount / 1000000 + " parrelStream:"
                + parallelTimeCount / 1000000+"\n");
    }


    public static void Test2(int count,int forCount){
        System.out.println("MapAddTest");
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            bankAccountDTO.setAccountNo(i + "");
            if (i % 4 == 0) {
                bankAccountDTO.setAccountNo(null);
            }
            bankAccountDTO.setBankName("name");
            bankAccountDTO.setBankCode("code");
            bankAccountDTOList.add(bankAccountDTO);
        }
        long forTimeCount = 0;

        long forEachTimeCount = 0;

        long streamTimeCount = 0;

        long parallelTimeCount = 0;



        for (int i = 0; i < forCount; i++) {
            Long fortimeStart = System.nanoTime();
            Map<String, BankAccountDTO> bankAccountDTOfor = new HashMap<>();
            for (BankAccountDTO bankAccountDTO : bankAccountDTOList) {
                if (bankAccountDTO.getAccountNo() != null && !bankAccountDTO.getAccountNo().isEmpty()) {
                    bankAccountDTOfor.put(bankAccountDTO.getAccountNo(),bankAccountDTO);
                }
            }
            Long forTime = (System.nanoTime() - fortimeStart);
            //System.out.println("forTime:" + forTime);
            forTimeCount += forTime;

            Long forEachtimeStart = System.nanoTime();
            Map<String, BankAccountDTO> bankAccountDTOforEach = new HashMap<>();
            bankAccountDTOList.forEach(bankAccountDTO ->{
                if (bankAccountDTO.getAccountNo() != null && !bankAccountDTO.getAccountNo().isEmpty()) {
                    bankAccountDTOforEach.put(bankAccountDTO.getAccountNo(),bankAccountDTO);
                }
            });
            Long forEachTime = System.nanoTime() - forEachtimeStart;
            //System.out.println("forEachTime:" + forEachTime);
            forEachTimeCount += forEachTime;

            Long streamTimeStart = System.nanoTime();
            Map<String, BankAccountDTO> bankAccountDTOStream = bankAccountDTOList.stream()
                .filter(account -> account.getAccountNo() != null && !account.getAccountNo().isEmpty())
                .collect(Collectors.toMap(BankAccountDTO::getAccountNo, Function.identity()));
            Long streamTime = (System.nanoTime() - streamTimeStart);
            //System.out.println("streamTime:" + streamTime);
            streamTimeCount += streamTime;

            Long parallelStreamTimeStart = System.nanoTime();
            Map<String, BankAccountDTO> bankAccountDTOparallelStream = bankAccountDTOList.parallelStream()
                .filter(account -> account.getAccountNo() != null && !account.getAccountNo().isEmpty())
                .collect(Collectors.toMap(BankAccountDTO::getAccountNo, Function.identity()));
            Long parallelStream = (System.nanoTime() - parallelStreamTimeStart);
            //System.out.println("parallelStream:" + parallelStream);
            parallelTimeCount += parallelStream;

            //System.out.println("length:" + bankAccountDTOfor.size() + " " + bankAccountDTOStream.size() + " "
            //    + bankAccountDTOparallelStream.size() + "\n");
        }

        System.out.println(
            "CountNonos forEach:" + forTimeCount + " forLam:" + forEachTimeCount + " stream:" + streamTimeCount + " parrelStream:"
                + parallelTimeCount);
        System.out.println(
            "CountMills forEach:" + forTimeCount / 1000000 + " forLam:" + forEachTimeCount / 1000000 + " stream:" + streamTimeCount / 1000000 + " parrelStream:"
                + parallelTimeCount / 1000000+"\n");

    }

    public static void TestSum(int count,int forCount) {
        System.out.println("ListSumTest\n");
        List<BankAccountDTO> bankAccountDTOList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            bankAccountDTO.setAccountNo(i + "");
            if (i % 4 == 0) {
                bankAccountDTO.setAccountNo(null);
            }
            bankAccountDTO.setBankName("name");
            bankAccountDTO.setBankCode("code");
            bankAccountDTOList.add(bankAccountDTO);
        }
        long forTimeCount = 0;
        long forEachTimeCount = 0;
        long forEachLamTimeCount = 0;
        long streamTimeCount = 0;
        long parallelTimeCount = 0;


        for (int i = 0; i < forCount; i++) {
            Long fortimeStart = System.nanoTime();
            Long forSum = 0L;
            for (int j = 0; j < bankAccountDTOList.size(); j++) {
                BankAccountDTO bankAccountDTO = bankAccountDTOList.get(j);
                if (bankAccountDTO.getAccountNo() != null && !bankAccountDTO.getAccountNo().isEmpty()) {
                    forSum += Long.valueOf(bankAccountDTO.getAccountNo());
                }
            }
            Long forTime = (System.nanoTime() - fortimeStart);
            //System.out.println("forTime:" + forTime);
            forTimeCount += forTime;

            Long forEachtimeStart = System.nanoTime();
            Wrapper forEachSum = new Wrapper();
            bankAccountDTOList.forEach(bankAccountDTO ->{
                if (bankAccountDTO.getAccountNo() != null && !bankAccountDTO.getAccountNo().isEmpty()) {
                    forEachSum.setValue(forEachSum.getValue() + Long.valueOf(bankAccountDTO.getAccountNo()));
                }
            });
            Long forEachTime = System.nanoTime() - forEachtimeStart;
            //System.out.println("forEachTime:" + forEachTime);
            forEachTimeCount += forEachTime;

            Long forEachLamtimeStart = System.nanoTime();
            Wrapper forEachLamSum = new Wrapper();
            bankAccountDTOList.forEach(bankAccountDTO ->{
                if (bankAccountDTO.getAccountNo() != null && !bankAccountDTO.getAccountNo().isEmpty()) {
                    forEachLamSum.setValue(forEachLamSum.getValue() + Long.valueOf(bankAccountDTO.getAccountNo()));
                }
            });
            Long forEachLamTime = System.nanoTime() - forEachLamtimeStart;
            //System.out.println("forEachTime:" + forEachTime);
            forEachLamTimeCount += forEachLamTime;

            Long streamTimeStart = System.nanoTime();
            Long streamSum = bankAccountDTOList.stream()
                .filter(account -> account.getAccountNo() != null && !account.getAccountNo().isEmpty())
                .map(account -> Long.valueOf(account.getAccountNo()))
                .reduce((x, y) -> x + y).get();
            Long streamTime = (System.nanoTime() - streamTimeStart);
            //System.out.println("streamTime:" + streamTime);
            streamTimeCount += streamTime;

            Long parallelStreamTimeStart = System.nanoTime();
            Long parallelStreamSum = bankAccountDTOList.parallelStream()
                .filter(account -> account.getAccountNo() != null && !account.getAccountNo().isEmpty())
                .map(account -> Long.valueOf(account.getAccountNo()))
                .reduce((x, y) -> x + y).get();
            Long parallelStream = (System.nanoTime() - parallelStreamTimeStart);
            //System.out.println("parallelStream:" + parallelStream);
            parallelTimeCount += parallelStream;

            //System.out.println("forSum:"+forSum+" forEachSum:"+forEachSum.getValue()+" streamSum:"+streamSum+" parallelSum:"+parallelStreamSum);
            //System.out.println("length:" + bankAccountDTOfor.size() + " " + bankAccountDTOStream.size()+ " " + bankAccountDTOparallelStream.size()+"\n");
        }
        System.out.println();
        System.out.println(
            "CountNanos for:" + forTimeCount + " forEach:" + forEachTimeCount +" forLam:"+forEachLamTimeCount +" stream:" + streamTimeCount + " parrelStream:"
                + parallelTimeCount);
        System.out.println(
            "CountMills for:" + forTimeCount / 1000000 + " forEach:" + forEachTimeCount / 1000000 + " forLam:"
                + forEachLamTimeCount / 1000000 + " stream:" + streamTimeCount / 1000000 + " parrelStream:"
                + parallelTimeCount / 1000000 + "\n");

    }
}
