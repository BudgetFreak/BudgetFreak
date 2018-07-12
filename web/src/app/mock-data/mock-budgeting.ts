export const BUDGETING = {
  carryover: 3000,
  income: 2800,
  _links: {
    self: '/users/42/budget/2018/11',
    previous: '/users/42/budget/2018/10',
    next: '/users/42/budget?year=2018&month=12',
  },
  _embedded: [
    {
      name: 'Wohnen',
      _links: {
        self: "/user/foo/mastercategory/1"
      },
      _embedded: [
        {
          name: 'Miete',
          budget: 600,
          expenses: 500,
          previousBudget: 600,
          previousExpenses: 500,
          averageExpenses: 530,
          _links: {
            self: "/user/foo/mastercategory/1/category/1"
          }
        },
        {
          name: 'Strom',
          budget: 50,
          expenses: 45,
          previousBudget: 55,
          previousExpenses: 52,
          averageExpenses: 49,
          _links: {
            self: "/user/foo/mastercategory/1/category/2"
          }
        },
        {
          name: 'Internet',
          budget: 35,
          expenses: 36.98,
          previousBudget: 40,
          previousExpenses: 38.50,
          averageExpenses: 42,
          _links: {
            self: "/user/foo/mastercategory/1/category/2"
          }
        },


      ]
    },
    {
      name: 'Auto',
      _links: {
        self: "/user/foo/mastercategory/2"
      },
      _embedded: [
        {
          name: 'Kraftstoff',
          budget: 250,
          expenses: 189,
          previousBudget: 220,
          previousExpenses: 210,
          averageExpenses: 200,
          _links: {
            self: "/user/foo/mastercategory/2/category/3"
          }
        },
        {
          name: 'Reparatur',
          budget: 0,
          expenses: 0,
          previousBudget: 350,
          previousExpenses: 330,
          averageExpenses: 20,
          _links: {
            self: "/user/foo/mastercategory/2/category/4"
          }
        },
        {
          name: 'Leasing',
          budget: 450,
          expenses: 410,
          previousBudget: 420,
          previousExpenses: 412,
          averageExpenses: 415,
          _links: {
            self: "/user/foo/mastercategory/2/category/5"
          }
        },

      ]
    }

  ]

};
