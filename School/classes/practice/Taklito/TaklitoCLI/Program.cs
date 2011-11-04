using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TaklitoData;

namespace TaklitoCLI
{
    class Program
    {
        IManageGroup manageGroup = new ManageGroup();
        public void ShowAllGroups()
        {
            List<Group> allGroup = manageGroup.getAllGroups();
            
            foreach (Group g in allGroup)
            {
                Console.WriteLine("the group name is :{0}", g.Name);
            }
            
        }

        public void AddNewGroup()

        {
            string name;
            Console.WriteLine("insert group name:");
            name = Console.ReadLine();
            manageGroup.AddGroup(name);
        }



        static void Main(string[] args)
        {
            Program p = new Program();
            Console.WriteLine("Welcome to Taklito : choose option");
            Console.WriteLine("ag :to add new group\n"+
                               "q: Exit\n"+ 
                               "view");
            string input =  Console.ReadLine();


            while (input != "q")  
            {
                input = input.ToLower().Trim();
                switch (input)
                {
                    case "q":
                        break;
                    case "add":
                        p.AddNewGroup();
                        break;
                    case "view":
                        p.ShowAllGroups();
                        break;
                }
               input = Console.ReadLine();

            } 


            
        }
    }
}
