db = db.getSiblingDB("company");
db.createCollection("init");

db.createUser(
        {
            user: "company",
            pwd: "password",
            roles: [
                {
                    role: "readWrite",
                    db: "company"
                }
            ]
        }
);