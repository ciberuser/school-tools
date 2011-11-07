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

        public bool AddArtistToGroup(Artist art, int groupID)
        {
            try
            {
                manageGroup.JoinArtistToGroup(art, groupID);
                return true;
            }
            catch (System.Exception)
            {
                return false;
            }
            
        }


        static void Main(string[] args)
        {
            Program p = new Program();
            Console.WriteLine("Welcome to Taklito : choose option");
            Console.WriteLine("add group:to add new group\n"+
                               "q: Exit\n"+ 
                               "view: view all groups\n " + 
                               "add_art: add artist \n" +
                               "create_art :create artist\n");

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
                    case "add_art":
                        Console.WriteLine("create new artist test:\nadd first,last and position");
                        Artist ar = new Artist();
                        ar.FirstName = Console.ReadLine();
                        ar.LastName = Console.ReadLine();
                        ar.Position = Console.ReadLine();

                        p.AddArtistToGroup(ar, 0);
                        break;

                }
               input = Console.ReadLine();

            } 


            
        }
    }
}
