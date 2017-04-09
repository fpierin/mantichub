import { ManticClientPage } from './app.po';

describe('mantic-client App', () => {
  let page: ManticClientPage;

  beforeEach(() => {
    page = new ManticClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
