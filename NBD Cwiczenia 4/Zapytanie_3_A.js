printjson(db.people.aggregate(
   [
     {
       $group:
         {
           _id: null, UniqueJobs: { $addToSet: "$job" }
         }
     }
   ]
).toArray())
