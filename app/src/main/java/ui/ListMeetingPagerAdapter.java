package ui;

/**
 * Created by Steve LEROY on 04/04/2020.
 */
public class ListMeetingPagerAdapter {


        public ListMeetingPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * getItem is called to instantiate the fragment for the given page.
         * @param position / integer : position de l'item sélectionné
         * @return : objet : instance du fragment sélectionné
         */
        @Override
        public Fragment getItem(int position) {
            if (position == 0) return NeighbourFragment.newInstance(false);
            else return  NeighbourFragment.newInstance(true);
        }

        /**
         * get the number of pages
         * @return : integer : retourne le nombre de pages du viewpage
         */
        @Override
        public int getCount() {
            return 2;
        }
    }
