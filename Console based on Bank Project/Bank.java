import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;
public class Bank
{
    static Account searchAccount(Vector<Account> vec,int AN)
	{
		Account ACC=null;
		for(int i=0;i<vec.size();i++)
		{
			ACC=(Account)vec.elementAt(i);
			if(ACC.getAccountNo()==AN)
				break;
		}
		return ACC;
	}
	public static void main(String args[]) throws Exception
	{
		Scanner scan=new Scanner(System.in);
		Vector<Account> vec=new Vector<>();
		int cmd;
		do
	{
		System.out.println("...............................");
		System.out.println("\tBanking System");
		System.out.println("...............................");
		System.out.println("1.Create New Account");
		System.out.println("2.Show Balance");
		System.out.println("3.Deposite");
		System.out.println("4.Withdraw");
		System.out.println("5.Interest");
		System.out.println("6.List Account");
		System.out.println("7.Save");
		System.out.println("8.Load");
		System.out.println("9.Exit");
		System.out.println("................................");
		System.out.println("Enter Command : ");
		cmd=scan.nextInt();
		int AN,AT;
		double BL;
		String FN,LN;
		Account ACC;
		switch(cmd)
		{
		case 1:
			System.out.println("Enter First Name:");
			FN=scan.next();
			System.out.println("Enter Last Name:");
			LN=scan.next();
			System.out.println("Enter Account Number:");
			AN=scan.nextInt();
			System.out.println("Balance:");
			BL=scan.nextInt();
			ACC=new Account(FN,LN,AN,BL);
			vec.add(ACC);
			System.out.println("Account created Successfully");
			break;
		case 2:
			System.out.println("Enter Account Number:");
			AN=scan.nextInt();
			ACC=searchAccount(vec,AN);
			ACC.showBalance();
			break;
		case 3:
			System.out.println("Enter Account Number");
			AN=scan.nextInt();
			System.out.println("Enter Amount:");
			AT=scan.nextInt();
			ACC=searchAccount(vec,AN);
			if(ACC==null)
				System.out.println("Account Not Found");
			else
			    ACC.deposit(AT);
			break;
		case 4:
			System.out.println("Enter Account Number");
			AN=scan.nextInt();
			System.out.println("Enter Amount:");
			AT=scan.nextInt();
			ACC=searchAccount(vec,AN);
			if(ACC==null)
				System.out.println("Account Not Found");
			else
				ACC.withdraw(AT);
			break;
		case 5:
			System.out.println("Enter Account Number:");
			AN=scan.nextInt();
			System.out.println("Enter Rate of Interest:");
			double RT=scan.nextDouble();
			System.out.println("Enter Number of Years:");
			int NY=scan.nextInt();
			ACC=searchAccount(vec,AN);
			if(ACC==null)
				System.out.println("Account Not Found");
			else
				ACC.addinterest(RT,NY);
			break;
            case 6:
            System.out.printf("| %10s | %15s | %10s |\n","Account no.","Name","Balance");
			for(Account item:vec)
            {
                AN=item.getAccountNo();
                FN=item.getFName();
                LN=item.getLName();
                BL=item.getBalance();
                System.out.printf("| %10d | %15s | %10.2f |\n",AN,FN+" "+LN,BL);
            }
            break;
            case 7:
            FileOutputStream fileout=new FileOutputStream("data.obj");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileout);
            objectOutputStream.writeObject(vec);
            System.out.println("Data stored....");
            break;
            case 8:
            FileInputStream filein=new FileInputStream("data.obj");
            ObjectInputStream objectInputStream=new ObjectInputStream(filein);
            vec=(Vector)objectInputStream.readObject();
            System.out.println("Data Loaded....");
		}
        try{
		System.out.println("Press enter to continue:");
		System.in.read();
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch(Exception e)
        {
            System.out.println(e);
        }
	}while(cmd!=9);
	}

}

