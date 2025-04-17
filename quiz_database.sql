-- Table: GameCategories (KategorijeIgara)
CREATE TABLE GameCategories (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code INTEGER NOT NULL,
    name TEXT NOT NULL,
    isActive BOOLEAN NOT NULL
);

-- Table: AnswerCategories (KategorijeOdgovora)
CREATE TABLE AnswerCategories (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    code INTEGER NOT NULL,
    name TEXT NOT NULL, -- e.g., "only one correct", "multiple correct"
    isActive BOOLEAN NOT NULL
);

-- Table: Questions (Pitanja)
CREATE TABLE Questions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    categoryId INTEGER NOT NULL,
    orderNumber INTEGER NOT NULL,
    questionText TEXT NOT NULL,
    answerCategoryId INTEGER NOT NULL,
    isActive BOOLEAN NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES GameCategories(id),
    FOREIGN KEY (answerCategoryId) REFERENCES AnswerCategories(id)
);

-- Table: Answers (Odgovori)
CREATE TABLE Answers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    questionId INTEGER NOT NULL,
    answerText TEXT NOT NULL,
    isCorrect BOOLEAN NOT NULL,
    isActive BOOLEAN NOT NULL,
    FOREIGN KEY (questionId) REFERENCES Questions(id)
);

-- Table: Users (Korisnici)
CREATE TABLE Users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT NOT NULL UNIQUE,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    isAdmin BOOLEAN NOT NULL,
    isActive BOOLEAN NOT NULL
);

-- Table: Games (Igre)
CREATE TABLE Games (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    categoryId INTEGER NOT NULL,
    startTime TEXT NOT NULL,       -- ISO datetime string (e.g., '2025-04-14T12:00:00')
    endTime TEXT,                  -- Can be NULL if game is still ongoing
    FOREIGN KEY (userId) REFERENCES Users(id),
    FOREIGN KEY (categoryId) REFERENCES GameCategories(id)
);

-- Table: GameQuestions (IgrePitanja)
CREATE TABLE GameQuestions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    gameId INTEGER NOT NULL,
    questionId INTEGER NOT NULL,
    score INTEGER NOT NULL CHECK (score IN (0, 1)),
    FOREIGN KEY (gameId) REFERENCES Games(id),
    FOREIGN KEY (questionId) REFERENCES Questions(id)
);

-- Table: GameAnswers (IgreOdgovori)
CREATE TABLE GameAnswers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    answerId INTEGER NOT NULL,
    gameQuestionId INTEGER NOT NULL,
    selectedAnswer BOOLEAN NOT NULL,
    FOREIGN KEY (answerId) REFERENCES Answers(id),
    FOREIGN KEY (gameQuestionId) REFERENCES GameQuestions(id)
);
