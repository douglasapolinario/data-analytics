library(rpart)
library(rpart.plot)
library(RColorBrewer)
library(rattle)

fit <- rpart(Survived ~ Pclass + Sex + Age + SibSp + Parch + Fare + Embarked, 
             data = train, 
             method = "class",
             control = rpart.control(minsplit = 4, cp = 0))
fancyRpartPlot(fit)

Prediction <- predict(fit, test, type="class")
submit <- data.frame(PassengerId = test$PassengerId, Survived = Prediction)
write.csv(submit, file = "~/Documents/MBA/data-analytics/titanic/myfirstdtree.csv", row.names = FALSE)

head(train)
head(test)
train$Name[1]
test$Survived <- NA
test$Child <- NA
test$Fare2 <- NA
combi <- rbind(train, test)
combi$Name <- as.character(combi$Name)
combi$Tittle <- sapply(combi$Name, FUN = function(x) {strsplit(x, split = '[,.]')[[1]][2]})
combi$Tittle <- sub(' ', '', combi$Tittle)
table(combi$Tittle)

