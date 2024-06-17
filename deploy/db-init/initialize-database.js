db.createUser(
    {
        user: "super",
        pwd: "super",
        roles: [
            { role: "readWrite", db: "contracts" },
        ]
    }
)
db = db.getSiblingDB('contracts');

db.createCollection("contracts");
db.createCollection("counters");
