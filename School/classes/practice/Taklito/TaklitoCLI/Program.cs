using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TaklitoDataDemo;

namespace TaklitoCLI
{
    class Program
    {
        IGroupManager manageGroup = new GroupManager();
        public void ShowAllGroups()
        {
            List<Group> allGroup = manageGroup.GetAllGroups();
            
            foreach (Group g in allGroup)
            {
                Console.WriteLine("the group id:{0} group name:{1}", g.GroupID,g.Name);
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
            Console.WriteLine("add group:to add new group\n"+
                               "q: Exit\n"+ 
                               "view: view all groups\n ");

            string input =  Console.ReadLine();


            while (input != "q")  
            {
                input = input.ToLower().Trim();
                switch (input)
                {
                    case "q":
                        break;
                    case "add group":
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
