# AppetencyOfBlood
Simulator of the medieval population growth and monarchy.

-From Overview.odt-

Disclaimer: this overview is very incomplete
Structure
    • Main.java will begin the simulation
    • Code-folder contains the actual code called by the aforementioned
    • Documentation-folder contain ramblings
    • Input-folder contains easily modifiable files
    • Output-folder will store the ever modifying files
      
Introduction
A hundred families will be generated of a varying size, each family has a patriarch and his wife, the matron. This number is a good number for it should rarely lead to extinction and result in a manageable number of people, which by the end of it should be decoupled of the initial number, which might be around two thousand people.

These two are of varying age and have varying number of children. Lives of these people and their descendants will be simulated from the year 1000 to 1500. During this time they will be ruled over by a monarch defined.

The Two Castes
Ten of these families are highborn and the rest lowborn. Both castes are only supposed to marry each other, but a highborn man might take a lowborn lover, and if there is a lack of highborn brides, he might marry him, such marriage is coined the morganatic marriage, and because highborn should not marry the lowborn, such marriage promotes to lowborn family of the bride into a new nobility. 

There must always at least ten families, therefore if a tenth family goes extinct, a lowborn house is randomly picked and raised into the nobility.

Benefits of highborn are as follows:
    • Patriarch of highborn families are eligible for election to the rulership
    • Every noble family has a coat of arms
    • Nobles can afford to raise any number of children, while lowborn man should not breed if he already has three living sons
    • Name of every noble house and cadet branch must be unique

Houses
There are two types of houses, "main houses" and "cadet houses". Every house will stem from a main house, when the house head dies (position which is determined agnatic primogeniture tradition) his sons will become the "princes of the house". When last of the princes has died, all the princes whose descended isn't the current head (that would be the senior branch) will form a cadet house. 
The idea being that houses of Lancaster and York would be formed upon the death of last of Edward III of England's sons, John of Gaunt's death, but if Richard II of England died before his uncle, the cadet house of Lancaster would not be founded because John's lineage would be the senior branch, but upon John's death his younger brother's descended would still form the cadet house of York.
So the leadership of a house should passed in following ways not other ways:
    1. From father to son
    2. From brother to brother
    3. From nephew to uncle
    4. From uncle to nephew, with a younger uncle still alive
    5. From cousin to cousin, with a younger uncle still alive
The nature of monogamous marriage dictates that every house will go extinct with time, but that doesn't mean they can't leave behind a cadet branch that too will go extinct, but that might also have cadet branches of its own, etc.
There is no mechanical bonuses for cadet branches. Only reason for branching is that families of hundreds members are not families but tribes and dull, the families should be small units of no more than twenty people.
Naming Patterns
Each family has a name pattern, this dictates how they will name their children. They also have a list of names used for both men and women. There are following methods:
1. Absolute orderly
Orderly will use the first unused name on the list, so if name list is {Henry, John, Richard} and the man already has living sons of Henry and John, the third son will be named Richard. If there are no unused names, a new name is randomly chosen and added into the list.
2. Weighted orderly
Similar to absolute orderly, but is random element based on Zipf's Law. Meaning if all three aforementioned example names are unused, there is 54.5% chance Henry will be used, 27.2% for John and 18.1% for Richard.
3. Flat
Random name with the same chance is picked from the family name list, hence all three names have 33% of being used.
4. Ancestral
Will try to use one of the two grandfather/grandmother's names, if not possible father/mother's name, otherwise parent's siblings, but as the last option it will resort to flat-pattern.
5. Parental
Will try to name their child after themselves, if not possible, resort to flat-pattern.
6. Altering
Instead of just using a single naming pattern, each time a child of is born they will pick a random pattern to use.
Origin
Each house a has a origin that describes how the house came to be. With following types:
0. Mystical, non-assigned, should not be used
1. Ancient, the family that predates the kingdom
2. Bastardy, the founder of the family was born outside of a wedlock
3. Posthumous, the founder of the family was born posthumously
4. Morganatic, a lowborn house was raised into the nobility as the result of a marriage to nobleman
5. Humble, a lowborn family was raised into the nobility as result of there being too few noble houses
The last two will be exclusive to nobility and overwrite other origins.

Offices
Offices are political entity that keep everything together. Each office has:
Realm, which is territory they rule.
Holders, the head of states.
Lineage, a list of holder claims.
Rulers, a list of head of government which often overlaps with holders, meaning this includes de facto rulers and child rulers would only be found from the Holders, while regent would be found from Rulers.
Treasury, where all funds of the state are stored.
RegnalNames, where names of the monarchs are stored.
Court, which a body of advisors of the monarch.
Consorts, which is a list of people who were married to a holder, albeit spouses that died before ascension of their spouse are not included here.
Military, that stores the troops of the office.

SexRelation
Every birth must originate from some union, which is a subclass of SexRelation, there type are:
1. Marriage
The most traditional type of union. Every child born from marriage will be legitimate. The limitation of marriage is that groom and the bride must be of the same estate; therefore, nobles marry nobles and peasants marry peasants.
2. Betrothal <planned>
A type of pre-marriage, that exists between an adult and a child. No children can be born of this and when the child reaches adulthood the union is disbanded and replaced with proper marriage.
3. Affair <planned>
Affairs is a sexual relationship between a man and a woman. It matters not if the man is married or not. However, if the woman is married each month there is a chance that the union will be discovered. If the affair is discovered all possible children from the union will be declared bastards born from the affair, even if that wouldn't be true. Furthermore, the husband will the demand the execution of his unfaithful wife, if the adulteress' partner is of greater status than the husband and is willing, the partner might save his wife's life by reforming the Affair into concubinage, otherwise, the wife will die.
4. Concubinage <planned>
Concubinage is an intermediate between a marriage and an affair. It exists between two people of non-equal status, but much like marriage is publicly known, but like affair all the children are illegitimate, yet unlike affair, the children born from such wedlock receive the status of their father, therefore while such children are barred from traditional succession, they might come into the play when the legitimate line goes extinct.
