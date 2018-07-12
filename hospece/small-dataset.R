library(rpart)
library(rpart.plot)
library(RColorBrewer)
library(rattle)

train <- read.csv("train-small.csv", stringsAsFactors=FALSE)
test <- read.csv("test-small.csv", stringsAsFactors=FALSE)
head(train)

fit <- rpart(died ~ sex + age + 
               respiratory_arrest + 
               respiratory_insufficiency + myocardial_infarction + 
               low_oxygen_saturation,
             data=train,
             method="class")

fancyRpartPlot(fit)

prediction <- predict(fit, test, type="class")
df_predict <- data.frame(Pacient = test$case, died = prediction)
write.csv(df_predict, file = "prediction2.csv", row.names = FALSE)


summary(prediction)
table(test$case, prediction)

train$died[train$died == "no"] <- 0
train$died[train$died == "yes"] <- 1
library(randomForest)
set.seed(415)
fit <- randomForest(as.numeric(died) ~ sex + age +
                    respiratory_arrest + respiratory_insufficiency + 
                    myocardial_infarction + low_oxygen_saturation,
                    data=train, 
                    importance=TRUE, 
                    ntree=2000)
varImpPlot(fit)
Prediction <- predict(fit, test)
submit <- data.frame(case = test$case, survived = Prediction)
write.csv(submit, file = "forest.csv", row.names = FALSE)



