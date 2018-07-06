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


