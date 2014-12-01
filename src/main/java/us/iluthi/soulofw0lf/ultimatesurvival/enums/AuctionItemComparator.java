package us.iluthi.soulofw0lf.ultimatesurvival.enums;

import us.iluthi.soulofw0lf.ultimatesurvival.customobjects.AuctionItem;

import java.util.Comparator;

/**
 * User: links
 * Date: 12/24/13
 * ... __                      __  ________
 * .. / /  ___ ________  ___  /  |/  / ___/
 * . / _ \/ _ `/ __/ _ \/ _ \/ /|_/ / /__
 * ./_.__/\_,_/\__/\___/_//_/_/  /_/\___/
 *
 * COMPARATOR FOR AUCTION ITEMS
 *
 * DEFINED SORTS: Size, Time, Minimum, Buyout
 * SUPPORTS MULTIPLE SORTS.
 *
 * EXAMPLE OF SINGLE SORT:
 *
 * List<AuctionItem> list = null;
 * Collections.sort(list, acsending(getComparator(BUYOUT_SORT)));
 *
 * EXAMPLE OF MULTIPLE SORT (SORTS BY BUYOUT_SORT, THEN BY SIZE_SORT!)
 *
 * List<AuctionItem> list = null;
 * Collections.sort(list, acsending(getComparator(BUYOUT_SORT, SIZE_SORT)));
 *
 *
 */
public enum AuctionItemComparator implements Comparator<AuctionItem> {
    SIZE_SORT {
        public int compare(AuctionItem o1, AuctionItem o2) {
            return Integer.valueOf(o1.getiS().getAmount()).compareTo(o2.getiS().getAmount());
        }},
    TIME_SORT {
        public int compare(AuctionItem o1, AuctionItem o2) {
            return Long.valueOf(o1.getTime()).compareTo(o2.getTime());
        }},
    MINIMUM_SORT {
        public int compare(AuctionItem o1, AuctionItem o2) {
            return Integer.valueOf(o1.getMinBid()).compareTo(o2.getMinBid());
        }},
    BUYOUT_SORT {
        public int compare(AuctionItem o1, AuctionItem o2) {
            return Integer.valueOf(o1.getBuyout()).compareTo(o2.getBuyout());
        }};

    public static Comparator<AuctionItem> decending(final Comparator<AuctionItem> other) {
        return new Comparator<AuctionItem>() {
            public int compare(AuctionItem o1, AuctionItem o2) {
                return -1 * other.compare(o1, o2);
            }
        };
    }

    public static Comparator<AuctionItem> getComparator(final AuctionItemComparator... multipleOptions) {
        return new Comparator<AuctionItem>() {
            public int compare(AuctionItem o1, AuctionItem o2) {
                for (AuctionItemComparator option : multipleOptions) {
                    int result = option.compare(o1, o2);
                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            }
        };
    }
}
