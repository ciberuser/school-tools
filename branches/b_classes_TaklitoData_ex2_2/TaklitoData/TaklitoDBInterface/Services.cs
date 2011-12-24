using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDBInterface
{
    class Services
    {
        public Services()
        {
            
        }

         public  static int createIntGuid()
        {
            byte[] seed = Guid.NewGuid().ToByteArray();
            for (int i = 0; i < 3; i++)
            {
                seed[i] ^= seed[i + 4];
                seed[i] ^= seed[i + 8];
                seed[i] ^= seed[i + 12];
            }

            return BitConverter.ToInt32(seed, 0);


        }

    }
}
