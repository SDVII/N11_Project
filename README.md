# N11 Project
An Automated Test project for the COP4428 N11 - Introduction to Software Testing coruse

The assignment was given as the following:

  I’d like you to make a project with selenium and maven. If you prefer to make your project without using java, you should use a dependency management, equivalent to maven in your preferred language.

  * You should verify that the scenario is working by creating a project
  * You should share your project with a git link (github / bitbucket vs.)
  * You should concern about software development standards as object oriented programming, names, code duplication etc. on your project as much as possible.

  Testing website: www.n11.com

  1. User should login with facebook (You can create a test facebook account).
  2. After successful login, http://www.n11.com should be open and you should verify that user has logged in successfully
  3. User should click “Kitap” category under "Kitap, Müzik, Film, Oyun” and under "Tüm Kategoriler” and then verify that the requested page has been loaded
  4. Clicks “Yazarlar” link from the left menu and verify that the requested page has been loaded
  5. Verify that the page has maximum 80 authors and all of the author’s firs character is right. You should verify all the pages. If there is more that 80 authors, there is pagination on the page. You don’s need to verify the pagination pages.
  6. If there is a pagination on the page, verify that the last author's name on the first page is not exist on the second page (check only second pages)

Notes: Please don't forget to change the WebDriver's path
