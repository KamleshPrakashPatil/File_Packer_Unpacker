import java.lang.*;
import java.util.*;
import java.io.*;

class Packer
{
    public void  Pack()
    {
        try
            {
                Scanner sobj = new Scanner(System.in);
    
                System.out.println("Please enter direcory / folder name");
                String foldername= sobj.nextLine();
    
                File dobj = new File(foldername);
    
                File allfiles[] = dobj.listFiles();
                String name;
    
                System.out.println("Please enter pack name");
                String packfilename= sobj.nextLine();
    
                File fobj=new File(packfilename);
                boolean bobj = fobj.createNewFile();
                FileOutputStream writerobj = new FileOutputStream(fobj);
    
                FileInputStream readerobj = null;
                int ret = 0;
                byte buffer[] = new byte[100];
                
                for(int i = 0; i < allfiles.length; i++)
                {
                        name = allfiles[i].getName();
    
                        if(name.endsWith(".txt"))
                        {
                            name = name +" "+(allfiles[i].length());
    
                            for(int j = name.length(); j<100; j++)
                            {
                                name = name + " ";
                            }
    
                            byte namearray[] = name.getBytes();
                            writerobj.write(namearray,0,namearray.length);
    
                            readerobj = new FileInputStream(allfiles[i]);
    
                            while((ret = readerobj.read(buffer)) != -1)
                            {
                                    writerobj.write(buffer,0,ret);
                            }
                            readerobj.close();
                        }
                }
                System.out.println("Your Files are Successfully Packed by name "+packfilename+" From Folder "+foldername);
            }
            catch(Exception obj)
            {
                System.out.println(obj);
            }
    }
}    

class Unpacker
{
     public void Unpack()
    {
        try
        {
            Scanner sobj = new Scanner(System.in);

            System.out.println("Enter packed file name");
            String PackFileName = sobj.nextLine();

            File fobj = new File(PackFileName);

            FileInputStream fiobj = new FileInputStream(fobj);
            byte Headerarray[] = new byte[100];

            String HeaderString = null;
            String tokens[];

            int ret = 0;

            while((ret = fiobj.read(Headerarray,0,100)) > 0)
            {
                      HeaderString = new String(Headerarray);
                      tokens = HeaderString.split(" ");

                      File fobjnew=new File(tokens[0]);
                      boolean bobj = fobjnew.createNewFile();

                      int size = Integer.parseInt(tokens[1]);
                      byte dataarray[] = new byte[size];

                      fiobj.read(dataarray,0,size);

                     FileOutputStream foobj = new FileOutputStream(fobjnew);
                     foobj.write(dataarray,0,size);
            }
            System.out.println("Your File is Successfully Unpacked");
        }
        catch(Exception obj)
        {
            System.out.println(obj);
        }
    }  
}

class Packerunpacker
{
    public static void main(String arg[])
    {
        Scanner sobj = new Scanner(System.in);    
        System.out.println("-------------------File Packer Unpacker-------------------");
        System.out.println("---------------------------------------------------------");

        while(true)
        {
            System.out.println("Enter 1 to Pack the file");
            System.out.println("Enter 2 to Unpack the file");
            System.out.println("Enter 3 to Exit the Application");

            System.out.print("Enter Your Choice : ");
            int Choice = sobj.nextInt();
    
            Packer pobj = new Packer();
    
            if(Choice == 1)
            {
                pobj.Pack();
            }
            else if(Choice == 2)
            {
                Unpacker uobj = new Unpacker();
    
                uobj.Unpack();
            }
            else if(Choice == 3)
            {
                break;
            }
            else
            {
                System.out.println("Please Choose Appropriate option");
            }
        }
        System.out.println("Application Closed");
    }
}