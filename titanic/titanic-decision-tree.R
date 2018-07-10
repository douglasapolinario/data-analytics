# Set working directory and import datafiles
setwd("~/Documents/MBA/data-analytics/titanic")
train <- read.csv("~/Documents/MBA/data-analytics/titanic/train.csv")
View(train)
test <- read.csv("~/Documents/MBA/data-analytics/titanic/test.csv")

train$Survived
sum(table(train$Survived))
sum(table(test$PassengerId))
table(train$Survived)
prop.table(table(train$Survived))
test$Survived <- rep(0, 418)
test$Survived

#cria um dataframe com 2 colunas e em seguida salva em um csv
submit <- data.frame(PassengerId = test$PassengerId, Survived = test$Survived)
write.csv(submit, file="~/Documents/MBA/data-analytics/titanic/theyallperish.csv", row.names = FALSE)

summary(train$Sex)
#proporcao por linha (%)
prop.table(table(train$Sex, train$Survived), 1)

#proporcao por coluna (%)
prop.table(table(train$Sex, train$Survived), 2)

#altera valor de Survived para 0 para todas linhas
#apos para 1 somente em linhas com Sex female
test$Survived <- 0
test$Survived[test$Sex == 'female'] <- 1

summary(train$Age)
train$Child <- 0
train$Child[train$Age < 18] <- 1

aggregate(Survived ~ Child + Sex, data=train, FUN=sum)
aggregate(Survived ~ Child + Sex, data=train, FUN=length)
aggregate(Survived ~ Child + Sex, data=train, FUN=function(x) {sum(x)/length(x)})

train$Fare2 <- '30+'
train$Fare2[train$Fare < 30 & train$Fare >= 20] <- '20-30'
train$Fare2[train$Fare < 20 & train$Fare >= 10] <- '10-20'
train$Fare2[train$Fare < 10] <- '<10'

aggregate(Survived ~ Fare2 + Pclass + Sex, data=train, FUN=function(x) {sum(x)/length(x)})

test$Survived <- 0
test$Survived[test$Sex == 'female'] <- 1
test$Survived[test$Sex == 'female' & test$Pclass == 3 & test$Fare >= 20] <- 0

submit <- data.frame(PassengerId = test$PassengerId, Survived = test$Survived)
write.csv(submit, file="~/Documents/MBA/data-analytics/titanic/theyallperish.csv", row.names = FALSE)

library(rpart)
fit <- rpart(Survived ~ Pclass + Sex + Age + SibSp + Parch + Fare + Embarked, 
             data = train, method = "class")
plot(fit)
text(fit)

install.packages('rattle')
install.packages('rpart.plot')
install.packages('RColorBrewer')
library(rpart.plot)
library(RColorBrewer)
library(rattle)
fancyRpartPlot(fit)

Prediction <- predict(fit, test, type="class")
submit <- data.frame(PassengerId = test$PassengerId, Survived = Prediction)
write.csv(submit, file = "~/Documents/MBA/data-analytics/titanic/myfirstdtree.csv", row.names = FALSE)
Prediction

